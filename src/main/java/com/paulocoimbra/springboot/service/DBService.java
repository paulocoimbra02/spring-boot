package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.*;
import com.paulocoimbra.springboot.domain.enums.ClientType;
import com.paulocoimbra.springboot.domain.enums.PaymentStatus;
import com.paulocoimbra.springboot.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

@Service
public class DBService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ItemOrderRepository itemOrderRepository;

    public void instantiateTestDatabase() throws ParseException {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");
        Category cat3 = new Category(null, "Cama, mesa e banho");
        Category cat4 = new Category(null, "Eletronicos");
        Category cat5 = new Category(null, "Jardinagem");
        Category cat6 = new Category(null, "Decoração");
        Category cat7 = new Category(null, "Perfumaria");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);
        Product p4 = new Product(null, "Mesa de escritório", 300.00);
        Product p5 = new Product(null, "Toalha", 50.00);
        Product p6 = new Product(null, "Colcha", 200.00);
        Product p7 = new Product(null, "TV true color", 1200.00);
        Product p8 = new Product(null, "Roçadeira", 800.00);
        Product p9 = new Product(null, "Abajour", 100.00);
        Product p10 = new Product(null, "Pendente", 180.00);
        Product p11 = new Product(null, "Shampoo", 90.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2, p4));
        cat3.getProducts().addAll(Arrays.asList(p5, p6));
        cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
        cat5.getProducts().addAll(Arrays.asList(p8));
        cat6.getProducts().addAll(Arrays.asList(p9, p10));
        cat7.getProducts().addAll(Arrays.asList(p11));

        p1.getCategories().addAll(Arrays.asList(cat1, cat4));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
        p3.getCategories().addAll(Arrays.asList(cat1, cat4));
        p4.getCategories().addAll(Arrays.asList(cat2));
        p5.getCategories().addAll(Arrays.asList(cat3));
        p6.getCategories().addAll(Arrays.asList(cat3));
        p7.getCategories().addAll(Arrays.asList(cat4));
        p8.getCategories().addAll(Arrays.asList(cat5));
        p9.getCategories().addAll(Arrays.asList(cat6));
        p10.getCategories().addAll(Arrays.asList(cat6));
        p11.getCategories().addAll(Arrays.asList(cat7));

        State st1 = new State(null, "Minas Gerais");
        State st2 = new State(null, "São paulo");

        City c1 = new City(null, "Uberlandia", st1);
        City c2 = new City(null, "São Paulo", st2);
        City c3 = new City(null, "Campinas", st2);

        st1.getCities().addAll(Arrays.asList(c1));
        st2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepository.saveAll(Arrays.asList(st1, st2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));

        Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "1231231232", ClientType.PESSOAFISICA);
        cli1.getPhoneNumbers().addAll(Arrays.asList("12312323", "867588758"));

        Address a1 = new Address(null, "Rua flores", "300", "312312", cli1, c1);
        Address a2 = new Address(null, "Avenida matos", "105", "484854", cli1, c2);

        cli1.getAddresses().addAll(Arrays.asList(a1, a2));

        clientRepository.saveAll(Arrays.asList(cli1));
        addressRepository.saveAll(Arrays.asList(a1, a2));

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Order1 ord1 = new Order1(null, sdf.parse("30/09/2017 10:32"), cli1, a1);
        Order1 ord2 = new Order1(null, sdf.parse("10/10/2017 19:35"), cli1, a2);

        Payment payment1 = new PaymentWithCard(null, PaymentStatus.SETTLED, ord1, 6);
        ord1.setPayment(payment1);

        Payment payment2 = new PaymentWithBill(null, PaymentStatus.PENDING, ord2, sdf.parse("20/10/2017 00:00"), null);
        ord2.setPayment(payment2);

        cli1.getOrders().addAll(Arrays.asList(ord1, ord2));

        orderRepository.saveAll(Arrays.asList(ord1, ord2));
        paymentRepository.saveAll(Arrays.asList(payment1, payment2));

        ItemOrder io1 = new ItemOrder(ord1, p1, 0.00, 1, 2000.00);
        ItemOrder io2 = new ItemOrder(ord1, p3, 0.00, 2, 80.00);
        ItemOrder io3 = new ItemOrder(ord2, p2, 100.00, 1, 800.00);

        ord1.getItems().addAll(Arrays.asList(io1, io2));
        ord2.getItems().addAll(Arrays.asList(io3));

        p1.getItems().addAll(Arrays.asList(io1));
        p2.getItems().addAll(Arrays.asList(io3));
        p3.getItems().addAll(Arrays.asList(io2));

        itemOrderRepository.saveAll(Arrays.asList(io1, io2, io3));

    }
}
