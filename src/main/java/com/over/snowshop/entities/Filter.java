package com.over.snowshop.entities;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "filter", schema = "snowshop")
public class Filter {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "filter", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<FilterValue> filterValues = new LinkedHashSet<>();

    @ManyToMany(mappedBy = "filters")
    private Set<Category> categories;

    @ManyToMany(mappedBy = "filters")
    private Set<Product> products;

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

    public Set<FilterValue> getFilterValues() {
        return filterValues;
    }

    public void setFilterValues(Set<FilterValue> filterValues) {
        this.filterValues = filterValues;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Filter)) return false;
        Filter filter = (Filter) o;
        if (!id.equals(filter.id)) return false;
        if (!name.equals(filter.name)) return false;
        if (filterValues != null ? !filterValues.equals(filter.filterValues) : filter.filterValues != null)
            return false;
        return categories != null ? categories.equals(filter.categories) : filter.categories == null;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
