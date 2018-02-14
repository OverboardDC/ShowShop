package com.over.snowshop.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "products", schema = "snowshop")
public class Product implements Serializable{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "image")
    private byte[] image;

    @Column(name = "sales")
    private int sales;

    @Column(name = "date")
    private Date date;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "productfiltervalue", schema = "snowshop"
            ,joinColumns = {
            @JoinColumn(name = "product_id")}, inverseJoinColumns = {
            @JoinColumn(name = "filter_id")
    })
    private Set<Filter> filters;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "productfiltervalue", schema = "snowshop"
            ,joinColumns = {
            @JoinColumn(name = "product_id")}, inverseJoinColumns = {
            @JoinColumn(name = "filtervalue_id")
    })
    private Set<FilterValue> filterValues;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "productorder", schema = "snowshop"
            ,joinColumns = {
            @JoinColumn(name = "product_id")}, inverseJoinColumns = {
            @JoinColumn(name = "order_id")
    })
    private Set<Order> orders;

    @OneToMany(mappedBy = "product")
    private Set<OrderedProduct> orderedProducts;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Set<FilterValue> getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(Set<FilterValue> filterValues) {
        this.filterValues = filterValues;
    }

    public Set<Filter> getFilters() {
        return filters;
    }

    public void setFilters(Set<Filter> filters) {
        this.filters = filters;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<OrderedProduct> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<OrderedProduct> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;

        Product product = (Product) o;

        if (!id.equals(product.id)) return false;
        if (!category.equals(product.category)) return false;
        if (!brand.equals(product.brand)) return false;
        if (!name.equals(product.name)) return false;
        if (!price.equals(product.price)) return false;
        return Arrays.equals(image, product.image);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + brand.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
