package com.over.snowshop.dao;

import com.over.snowshop.entities.Product;
import com.over.snowshop.objects.Paginator;
import com.over.snowshop.objects.SortingType;
import com.over.snowshop.objects.SortingTypesMap;
import com.over.snowshop.utils.DaoUtil;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDaoImpl implements ProductDao {

    private SessionFactory sessionFactory;

    @Override
    public Product getProduct(Long id) {
        return (Product) getSession().createCriteria(Product.class).add(Restrictions.eq("id", id)).uniqueResult();
    }

    @Override
    public List getProductsByCategory(Long categoryId, String sortType, Long brandId, Integer page) {
        Criteria criteria = createProductListCriteria().add(Restrictions.eq("category.id", categoryId));
        sortCriteria(criteria, sortType);
        addBrandRestriction(criteria, brandId);
        Paginator.setRowsCount((long) criteria.list().size());
        setLimits(criteria, page);
        return criteria.list();
    }

    @Override
    public List getProductsByBrand(Long brandId, String sortType, Integer page) {
        Criteria criteria = createProductListCriteria().add(Restrictions.eq("brand.id", brandId));
        sortCriteria(criteria, sortType);
        Paginator.setRowsCount((long) criteria.list().size());
        setLimits(criteria, page);
        return criteria.list();
    }

    @Override
    public List getProductBySearch(String query, String sortType, Long brandId, Integer page) {
        Disjunction disjunction = Restrictions.disjunction();
        disjunction.add(Restrictions.like("name", query, MatchMode.ANYWHERE));
        disjunction.add(Restrictions.like("brand.name", query, MatchMode.ANYWHERE));
        Criteria criteria = createProductListCriteria().add(disjunction);
        sortCriteria(criteria, sortType);
        addBrandRestriction(criteria, brandId);
        Paginator.setRowsCount((long) criteria.list().size());
        setLimits(criteria, page);
        return criteria.list();
    }

    @Override
    public List getLatestProducts() {
        return createProductListCriteria().addOrder(Order.desc("date")).setMaxResults(4).list();
    }

    @Override
    public List getBestsellers() {
        return createProductListCriteria().addOrder(Order.desc("sales")).setMaxResults(4).list();
    }


    @Override
    public byte[] getImage(Long productId) {
        return (byte[]) DaoUtil.getFieldValue(productId, Product.class, "image", getSession());
    }

    private Criteria createProductListCriteria() {
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.createAlias("brand", "brand");
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.property("id"), "id");
        projectionList.add(Projections.property("name"), "name");
        projectionList.add(Projections.property("price"), "price");
        projectionList.add(Projections.property("brand"), "brand");
        criteria.setProjection(projectionList);
        criteria.setResultTransformer(Transformers.aliasToBean(Product.class));
        return criteria;
    }

    private void sortCriteria(Criteria criteria, String sortType) {
        for (SortingType sortingType : SortingTypesMap.getMap().values()) {
            sortingType.setChosen(false);
        }

        if (sortType != null) {
            Order order = Order.asc("name");
            SortingType sortingType = SortingTypesMap.getMap().get(sortType);
            switch (sortingType.getName()) {
                case "featured":
                    order = Order.asc("name");
                    break;
                case "highest_rated":
                    order = Order.asc("name");
                    break;
                case "Highest Price":
                    order = Order.desc("price");
                    break;
                case "Lowest Price":
                    order = Order.asc("price");
                    break;
                case "new_arrivals":
                    order = Order.desc("date");
                    break;
            }
            sortingType.setChosen(true);
            criteria.addOrder(order);
        } else {
            SortingTypesMap.getMap().get("new_arrivals").setChosen(true);
        }
    }

    private void addBrandRestriction(Criteria criteria, Long brandId) {
        if (brandId != null) {
            criteria.add(Restrictions.eq("brand.id", brandId));
        }
    }

    private void setLimits(Criteria criteria, int page) {
        criteria.setFirstResult(page * Paginator.getProductsOnPage() - Paginator.getProductsOnPage())
                .setMaxResults(Paginator.getProductsOnPage());
    }


    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }


    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
