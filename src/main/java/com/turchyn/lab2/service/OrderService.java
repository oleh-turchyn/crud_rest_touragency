package com.turchyn.lab2.service;

import com.turchyn.lab2.model.Order;
import com.turchyn.lab2.model.OrderId;
import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;

public interface OrderService {
    public Order save(@NonNull Order object);

    public void removeById(@NonNull OrderId id);

    public Optional<Order> findById(@NonNull OrderId id);

    public @NonNull
    Collection<Order> findAll();
}
