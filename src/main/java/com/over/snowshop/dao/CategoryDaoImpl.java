package com.over.snowshop.dao;

import com.over.snowshop.entities.Brand;
import com.over.snowshop.entities.Category;
import com.over.snowshop.entities.Section;
import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao{

    private SessionFactory sessionFactory;

    @Override
    public List getSections() {
        return getSession().createCriteria(Section.class).setFetchMode("categories", FetchMode.SELECT).list();
    }

    @Override
    public Category getCurrentCategory(Long categoryId) {
        return (Category) getSession().createCriteria(Category.class).add(Restrictions.eq("id", categoryId)).uniqueResult();
    }



    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
