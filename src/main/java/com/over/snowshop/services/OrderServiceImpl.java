package com.over.snowshop.services;

import com.over.snowshop.dao.OrderDao;
import com.over.snowshop.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;

    @Override
    @Transactional
    public void formOrder(Order order) {
        orderDao.formOrder(order);
    }

    @Autowired
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
}
