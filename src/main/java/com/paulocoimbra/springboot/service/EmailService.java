package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Order1;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order1 order);

    void sendEmail(SimpleMailMessage msg);
}
