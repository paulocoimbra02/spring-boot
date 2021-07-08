package com.paulocoimbra.springboot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Entity
public class Order1 implements Serializable {

    private static final long serialVersionUID = 144400346356575925L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
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

    public double getTotalValue() {
        double sum = 0;
        for (ItemOrder io : items) {
            sum += io.getSubTotal();
        }
        return sum;
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

    @Override
    public String toString() {
        NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        final StringBuilder sb = new StringBuilder();
        sb.append("Order number: ");
        sb.append(getId());
        sb.append(", Placement: ");
        sb.append(sdf.format(getPlacement()));
        sb.append(", Client: ");
        sb.append(getClient().getName());
        sb.append(", Payment status: ");
        sb.append(getPayment().getPaymentStatus().getDescription());
        sb.append("\nDetalhes:\n");
        for (ItemOrder io : getItems()) {
            sb.append(io.toString());
        }
        sb.append("Total value: ");
        sb.append(nf.format(getTotalValue()));
        return sb.toString();
    }
}
