package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.PaymentWithBill;
import com.paulocoimbra.springboot.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BillService {

    @Autowired
    private OrderRepository repo;

    public void fillPaymentWithBill(PaymentWithBill payment, Date orderPlacement) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(orderPlacement);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        payment.setExpirationDate(cal.getTime());
    }
}
