package com.paulocoimbra.springboot.service;

import com.paulocoimbra.springboot.domain.Client;
import com.paulocoimbra.springboot.domain.Order1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

public abstract class AbstractEmailService implements EmailService {

    @Value("${default.sender}")
    private String sender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private JavaMailSender javaMailSender;

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

    protected String htmlFromTemplatePedido(Order1 order) {
        Context context = new Context();
        context.setVariable("order", order);
        return templateEngine.process("email/orderConfirmation", context);
    }

    @Override
    public void sendOrderConfirmationHtmlEmail(Order1 order) {
        MimeMessage mm = null;
        try {
            mm = prepareMimeMessageFromOrder(order);
            sendHtmlEmail(mm);
        } catch (MessagingException e) {
            sendOrderConfirmationEmail(order);
        }
    }

    private MimeMessage prepareMimeMessageFromOrder(Order1 order) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
        mmh.setTo(order.getClient().getEmail());
        mmh.setFrom(sender);
        mmh.setSubject("Order confirmed! Number: " + order.getId());
        mmh.setSentDate(new Date(System.currentTimeMillis()));
        mmh.setText(htmlFromTemplatePedido(order), true);
        return mimeMessage;
    }

    @Override
    public void sendNewPasswordEmail(Client client, String newPass) {
        SimpleMailMessage sm = prepareNewMailMessageFromOrder(client, newPass);
        sendEmail(sm);
    }

    protected SimpleMailMessage prepareNewMailMessageFromOrder(Client client, String newPass){
        SimpleMailMessage sm = new SimpleMailMessage();

        sm.setTo(client.getEmail());
        sm.setFrom(sender);
        sm.setSubject("New password request");
        sm.setSentDate(new Date(System.currentTimeMillis()));
        sm.setText("New password: " + newPass);

        return sm;
    }


}
