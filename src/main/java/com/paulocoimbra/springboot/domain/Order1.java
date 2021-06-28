package com.paulocoimbra.springboot.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Order1 implements Serializable {

    private static final long serialVersionUID = 144400346356575925L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Date placement;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order1")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "shipment_address_id")
    private Address shipmentAddress;

    @OneToMany(mappedBy = "id.order")
    private Set<ItemOrder> items = new HashSet<>();

    public Order1() {
    }

    public Order1(Integer id, Date placement, Client client, Address shipmentAddress) {
        this.id = id;
        this.placement = placement;
        this.client = client;
        this.shipmentAddress = shipmentAddress;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getPlacement() {
        return placement;
    }

    public void setPlacement(Date placement) {
        this.placement = placement;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Address getShipmentAddress() {
        return shipmentAddress;
    }

    public void setShipmentAddress(Address shipmentAddress) {
        this.shipmentAddress = shipmentAddress;
    }

    public Set<ItemOrder> getItems() {
        return items;
    }

    public void setItems(Set<ItemOrder> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order1 order1 = (Order1) o;
        return Objects.equals(id, order1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
