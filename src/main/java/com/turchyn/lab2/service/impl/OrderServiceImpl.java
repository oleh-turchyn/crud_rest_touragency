package com.turchyn.lab2.service.impl;

import com.turchyn.lab2.model.Order;
import com.turchyn.lab2.model.OrderId;
import com.turchyn.lab2.repository.OrderRepository;
import com.turchyn.lab2.repository.TourRepository;
import com.turchyn.lab2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository=orderRepository;
    }


    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void removeById(OrderId id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(OrderId id) {
        return orderRepository.findById(id);
    }

    @Override
    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }
}
