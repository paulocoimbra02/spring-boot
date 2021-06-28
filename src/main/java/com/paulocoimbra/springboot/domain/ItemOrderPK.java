package com.paulocoimbra.springboot.domain;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;


@Embeddable
public class ItemOrderPK implements Serializable {

    private static final long serialVersionUID = 4340105605319628951L;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order1 order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Order1 getOrder() {
        return order;
    }

    public void setOrder(Order1 order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemOrderPK that = (ItemOrderPK) o;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
