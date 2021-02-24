package com.turchyn.lab2.controller;

import com.turchyn.lab2.exception.UserNotFoundException;
import com.turchyn.lab2.model.Order;
import com.turchyn.lab2.model.OrderId;
import com.turchyn.lab2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;

@CrossOrigin(origins = "http://localhost:9191")
@RestController
@RequestMapping("/api")
public class OrderController {
    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    ResponseEntity<List<Order>> findAllOrders() {
        try {
            List<Order> orders = new ArrayList<>(orderService.findAll());

            if (orders.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(orders, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/orderById/{firstKey}_{secondKey}",  produces = TEXT_PLAIN_VALUE)
    public ResponseEntity<Order> findOrderById(@PathVariable int firstKey, @PathVariable int secondKey) {
        OrderId orderId = new OrderId(firstKey,secondKey);
        Optional<Order> user = Optional.ofNullable(orderService.findById(orderId)
                .orElseThrow(() -> new UserNotFoundException("Order with " + orderId + " is Not Found!")));
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/orders")
    public ResponseEntity<Order> saveOrder(@RequestBody Order order) {
        try {
            Order createdOrder = orderService
                    .save(order);
            return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/orders/{firstKey}_{secondKey}")
    public ResponseEntity<Order> updateOrder(@PathVariable int firstKey, @PathVariable int secondKey, @RequestBody Order order) {
        OrderId orderId = new OrderId(firstKey,secondKey);
        Optional<Order> orderData = Optional.ofNullable(orderService
                .findById(orderId).orElseThrow(() -> new UserNotFoundException("Order with " + orderId + " is Not Found!")));

        if (orderData.isPresent()) {
            Order updatedOrder = orderData.get();
            updatedOrder.setUser(order.getUser());
            updatedOrder.setTour(order.getTour());
            updatedOrder.setOrderDate(order.getOrderDate());
            return new ResponseEntity<>(orderService.save(updatedOrder), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/orders/{firstKey}_{secondKey}")
    public ResponseEntity<HttpStatus> removeOrder(@PathVariable int firstKey, @PathVariable int secondKey) {
        try {
            OrderId orderId = new OrderId(firstKey,secondKey);
            Order order = orderService.findById(orderId)
                    .orElseThrow(() -> new UserNotFoundException("Order with " + orderId + " is Not Found!"));
            orderService.removeById(order.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
