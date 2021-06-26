package com.paulocoimbra.springboot.domain;

import com.paulocoimbra.springboot.domain.enums.PaymentStatus;

import javax.persistence.Entity;

@Entity
public class PaymentWithCard extends Payment {

    private static final long serialVersionUID = 144400346356575925L;

    private Integer numberOfInstallments;

    public PaymentWithCard() {
    }

    public PaymentWithCard(Integer id, PaymentStatus paymentStatus, Order1 order1, Integer numberOfInstallments) {
        super(id, paymentStatus, order1);
        this.numberOfInstallments = numberOfInstallments;
    }

    public Integer getNumberOfInstallments() {
        return numberOfInstallments;
    }

    public void setNumberOfInstallments(Integer numberOfInstallments) {
        this.numberOfInstallments = numberOfInstallments;
    }
}
