package com.turchyn.lab2.repository;

import com.turchyn.lab2.model.Order;
import com.turchyn.lab2.model.OrderId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, OrderId> {

}
