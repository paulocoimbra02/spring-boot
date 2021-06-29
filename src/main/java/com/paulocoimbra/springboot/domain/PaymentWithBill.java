package com.paulocoimbra.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paulocoimbra.springboot.domain.enums.PaymentStatus;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class PaymentWithBill extends Payment {

    private static final long serialVersionUID = 144400346356575925L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date expirationDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date paymentDate;


    public PaymentWithBill() {
    }

    public PaymentWithBill(Integer id, PaymentStatus paymentStatus, Order1 order1, Date expirationDate, Date paymentDate) {
        super(id, paymentStatus, order1);
        this.expirationDate = expirationDate;
        this.paymentDate = paymentDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }
}
