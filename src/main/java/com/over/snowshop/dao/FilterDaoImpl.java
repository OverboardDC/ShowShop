package com.over.snowshop.dao;

import com.over.snowshop.entities.Filter;
import com.over.snowshop.entities.FilterValue;
import org.hibernate.FetchMode;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.ResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

//Unused
@Repository
public class FilterDaoImpl implements FilterDao {

    private SessionFactory sessionFactory;

    @Override
    public List getFiltersByCategory(Long categoryId, Map<String, Long> lastFilters) {
        List<Filter> filters = getSession().createCriteria(Filter.class).createAlias("categories", "categories")
                .add(Restrictions.eq("categories.id", categoryId)).setFetchMode("filterValues", FetchMode.SELECT).list();
       for (Filter filter : filters){
           for(FilterValue filterValue : filter.getFilterValues()){
                if(filterValue.getId().equals(lastFilters.get(filterValue.getId().toString()))){
                    filterValue.setChosen(true);
                }
           }
       }
        return filters;
    }

    private Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
