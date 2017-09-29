package com.omotyliu.Order.repository;

import com.omotyliu.Order.Order;
import com.omotyliu.Order.service.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgreOrderRepository implements OrderRepository{

    @Override
    public Order getOrder(Long id) {
        System.out.println("return Order");
        return null;
    }

    @Override
    public Long saveOrder(Order order) {
        System.out.println("save Order");
        return null;
    }

    @Override
    public List<Order> getCustomerOrders(Long clientId) {
        System.out.println("return Customer orderList");
        return null;
    }

    @Override
    public List<Order> getAll(Integer limit) {
        System.out.println("return Order List all");
        return null;
    }
}
