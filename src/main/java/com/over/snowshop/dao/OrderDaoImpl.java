package com.over.snowshop.dao;

import com.over.snowshop.entities.Order;
import com.over.snowshop.entities.OrderedProduct;
import com.over.snowshop.objects.Cart;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public class OrderDaoImpl implements OrderDao {

    private SessionFactory sessionFactory;

    @Override
    public void formOrder(Order order) {
        order.setDate(new Date());
        order.setOrderedProducts(Cart.getCart());
        getSession().persist(order);
        for(OrderedProduct orderedProduct : order.getOrderedProducts()){
            orderedProduct.setOrder(order);
            getSession().persist(orderedProduct);
        }
        Cart.clearCart();
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
