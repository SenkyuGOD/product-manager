package com.example.product_manager_v2.service.impl;

import com.example.product_manager_v2.dao.DaoException;
import com.example.product_manager_v2.dao.OrderItemDao;
import com.example.product_manager_v2.entity.OrderItem;
import com.example.product_manager_v2.service.OrderItemService;
import com.example.product_manager_v2.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {

    private final OrderItemDao orderItemDao;

    @Autowired
    public OrderItemServiceImpl(OrderItemDao orderItemDao) {
        this.orderItemDao = orderItemDao;
    }

    @Override
    public List<OrderItem> findAll() throws ServiceException {
        try {
            return orderItemDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderItem save(OrderItem orderItem) throws ServiceException {
        try {
            return orderItemDao.save(orderItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public OrderItem update(Long id, OrderItem orderItemDetails) throws ServiceException {
        try {
            OrderItem orderItem = orderItemDao.findById(id);
            orderItem.setOrder(orderItemDetails.getOrder());
            orderItem.setProduct(orderItemDetails.getProduct());
            orderItem.setQuantity(orderItemDetails.getQuantity());
            return orderItemDao.save(orderItem);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Long id) throws ServiceException {
        try {
            orderItemDao.deleteById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    public OrderItem findById(Long id) throws ServiceException {
        try {
            return orderItemDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
