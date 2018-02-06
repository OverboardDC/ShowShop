package com.over.snowshop.dao;

import com.over.snowshop.entities.Brand;
import com.over.snowshop.entities.Product;
import com.over.snowshop.utils.DaoUtil;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BrandDaoImpl implements BrandDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Brand> getBrandsByCategory(Long categoryId, Long chosenBrandId) {
       List<Brand> brands = createBrandCriteria().add(Restrictions.eq("category.id", categoryId))
               .setProjection(Projections.distinct(Projections.property("brand")))
               .list();
       for(Brand brand : brands) {
           if (brand.getId().equals(chosenBrandId)) {
               brand.setChosen(true);
           }
       }
       return brands;
    }

    @Override
    public List<Brand> getBrandsBySearch(String query, Long chosenBrandId) {
        List<Brand> brands = createBrandCriteria().add(Restrictions.ilike("name", query, MatchMode.ANYWHERE))
                .setProjection(Projections.distinct(Projections.property("brand")))
                .list();
        for(Brand brand : brands) {
            if (brand.getId().equals(chosenBrandId)) {
                brand.setChosen(true);
            }
        }
        return brands;
    }


    @Override
    public List getMainBrands() {
        return getSession().createCriteria(Brand.class).setMaxResults(8).list();
    }

    @Override
    public Brand getCurrentBrand(Long brandId) {
        return (Brand) getSession().createCriteria(Brand.class).add(Restrictions.eq("id", brandId)).uniqueResult();
    }

    @Override
    public byte[] getBrandLogo(Long brandId) {
        return (byte[]) DaoUtil.getFieldValue(brandId, Brand.class, "logo", getSession());
    }

    private Criteria createBrandCriteria(){
        Criteria criteria = getSession().createCriteria(Product.class);
        criteria.setProjection(Projections.property("brand.id"));
        criteria.setProjection(Projections.property("brand.name"));
        return criteria;
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
