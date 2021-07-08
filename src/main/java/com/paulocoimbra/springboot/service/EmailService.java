package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Order1;
import org.springframework.mail.SimpleMailMessage;

import javax.mail.internet.MimeMessage;

public interface EmailService {

    void sendOrderConfirmationEmail(Order1 order);

    void sendEmail(SimpleMailMessage msg);

    void sendOrderConfirmationHtmlEmail(Order1 order1);

    void sendHtmlEmail(MimeMessage msg);
}
