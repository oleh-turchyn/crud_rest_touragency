package com.turchyn.lab2.service.impl;

import com.turchyn.lab2.model.Order;
import com.turchyn.lab2.model.Tour;
import com.turchyn.lab2.model.User;
import com.turchyn.lab2.repository.OrderRepository;
import com.turchyn.lab2.repository.TourRepository;
import com.turchyn.lab2.repository.UserRepository;
import com.turchyn.lab2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {
    OrderRepository orderRepository;
    UserRepository userRepository;
    TourRepository tourRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, UserRepository userRepository, TourRepository tourRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.tourRepository = tourRepository;
    }


    @Override
    public Order save(Order order) {
        User user = userRepository.findById(order.getUser().getId()).orElse(null);
        if(user == null){
            user = new User();
        }
        user.setName(order.getUser().getName());
        user.setSurname(order.getUser().getSurname());
        order.setUser(user);
        Tour tour = tourRepository.findById(order.getTour().getId()).orElse(null);
        if(tour == null){
            tour = new Tour();
        }
        tour.setTitle(order.getTour().getTitle());
        tour.setPrice(order.getTour().getPrice());
        order.setTour(tour);
        return orderRepository.save(order);
    }

    @Override
    public void removeById(Integer id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<Order> findById(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public Collection<Order> findAll() {
        return orderRepository.findAll();
    }
}
