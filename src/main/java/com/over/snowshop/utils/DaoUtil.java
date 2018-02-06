package com.over.snowshop.utils;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

public class DaoUtil {

    public static Object getFieldValue(Long id, Class entity, String field, Session session) {
        Criteria criteria = session.createCriteria(entity);
        criteria.setProjection(Projections.property(field));
        criteria.add(Restrictions.eq("id", id));
        return criteria.uniqueResult();
    }
}
