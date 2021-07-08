package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.ItemOrder;
import com.paulocoimbra.springboot.domain.Order1;
import com.paulocoimbra.springboot.domain.PaymentWithBill;
import com.paulocoimbra.springboot.domain.enums.PaymentStatus;
import com.paulocoimbra.springboot.repository.ClientRepository;
import com.paulocoimbra.springboot.repository.ItemOrderRepository;
import com.paulocoimbra.springboot.repository.OrderRepository;
import com.paulocoimbra.springboot.repository.PaymentRepository;
import com.paulocoimbra.springboot.service.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repo;

    @Autowired
    private BillService billService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private EmailService emailService;

    public Order1 findById(Integer id) {
        Optional<Order1> order = repo.findById(id);
        return order.orElseThrow(() -> new ObjectNotFoundException(
                "Object not found! Id: " + id + ", Type: " + Order1.class.getName()));
    }

    @Transactional
    public Order1 insert(Order1 order) {
        order.setId(null);
        order.setPlacement(new Date());
        order.setClient(clientService.findById(order.getClient().getId()));
        order.getPayment().setPaymentStatus(PaymentStatus.PENDING);
        order.getPayment().setOrder(order);
        if (order.getPayment() instanceof PaymentWithBill) {
            PaymentWithBill payment = (PaymentWithBill) order.getPayment();
            billService.fillPaymentWithBill(payment, order.getPlacement());
        }
        order = repo.save(order);
        paymentRepository.save(order.getPayment());
        for (ItemOrder io : order.getItems()) {
            io.setDiscount(0.0);
            io.setProduct(productService.findById(io.getProduct().getId()));
            io.setPrice(io.getProduct().getPrice());
            io.setOrder(order);
        }
        itemOrderRepository.saveAll(order.getItems());
        emailService.sendOrderConfirmationHtmlEmail(order);

        return order;
    }
}
