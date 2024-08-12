package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.DaoException;
import com.example.product_manager_v2.dao.OrderDao;
import com.example.product_manager_v2.entity.Order;
import com.example.product_manager_v2.service.OrderService;
import com.example.product_manager_v2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public List<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order save(Order order) throws ServiceException {
        try {
            return orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Order update(Long id, Order orderDetails) throws ServiceException {
        try {
            Order order = orderDao.findById(id);
            order.setStatus(orderDetails.getStatus());
            order.setCustomer(orderDetails.getCustomer());
            return orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            orderDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }


    @Override
    public Order findById(Long id) throws ServiceException {
        try {
            return orderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
