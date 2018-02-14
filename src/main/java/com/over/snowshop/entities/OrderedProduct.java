package com.over.snowshop.entities;

import javax.persistence.*;

@Entity
@Table(name = "productorder", schema = "snowshop")
public class OrderedProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "id")
    private Order order;

    @Column(name = "quantity")
    private int quantity;

    public OrderedProduct(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public OrderedProduct() {
    }

    public double getFullPrice(){
        return product.getPrice() * this.quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderedProduct{" +
                "id=" + id +
                ", product=" + product +
                ", order=" + order +
                ", quantity=" + quantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderedProduct)) return false;

        OrderedProduct that = (OrderedProduct) o;

        if (quantity != that.quantity) return false;
        if (!id.equals(that.id)) return false;
        if (!product.equals(that.product)) return false;
        return order.equals(that.order);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + product.hashCode();
        result = 31 * result + order.hashCode();
        result = 31 * result + quantity;
        return result;
    }
}
