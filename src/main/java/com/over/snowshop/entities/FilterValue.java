package com.over.snowshop.entities;

import javax.persistence.*;
import java.util.Set;


@Entity
@Table(name = "filtervalue", schema = "snowshop")
public class FilterValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "filter_id")
    private Filter filter;


    @ManyToMany(mappedBy = "filterValues")
    private Set<Product> products;

    @Transient
    private boolean isChosen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public boolean isChosen() {
        return isChosen;
    }

    public void setChosen(boolean chosen) {
        isChosen = chosen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FilterValue)) return false;

        FilterValue that = (FilterValue) o;

        if (!id.equals(that.id)) return false;
        if (!name.equals(that.name)) return false;
        return filter.equals(that.filter);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + filter.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FilterValue{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", filter=" + filter +
                '}';
    }
}
