package com.paulocoimbra.springboot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.paulocoimbra.springboot.domain.enums.PaymentStatus;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable {

    private static final long serialVersionUID = 144400346356575925L;

    @Id
    private Integer id;
    private Integer paymentStatus;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "order_id")
    @MapsId
    private Order1 order1;

    public Payment() {
    }

    public Payment(Integer id, PaymentStatus paymentStatus, Order1 order1) {
        this.id = id;
        this.paymentStatus = paymentStatus.getCod();
        this.order1 = order1;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PaymentStatus getPaymentStatus() {
        return PaymentStatus.toEnum(paymentStatus);
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus.getCod();
    }

    @JsonIgnore
    public Order1 getOrder() {
        return order1;
    }

    public void setOrder(Order1 order1) {
        this.order1 = order1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
