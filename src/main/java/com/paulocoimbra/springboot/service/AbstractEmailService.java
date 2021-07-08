package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Order1;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Override
    public void sendOrderConfirmationEmail(Order1 order) {
        SimpleMailMessage sm = prepareSimpleMailMessageFromOrder(order);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromOrder(Order1 order) {
        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo(order.getClient().getEmail());
        sm.setFrom(sender);
        sm.setSubject("Order confirmed! Number: " + order.getId());
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText(order.toString());

        return sm;
    }
}
