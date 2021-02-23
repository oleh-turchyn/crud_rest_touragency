package com.turchyn.lab2.service;

import org.springframework.lang.NonNull;

import java.util.Collection;
import java.util.Optional;

public interface AbstractDomainObjectService <T>{
    public T save(@NonNull T object);

    public void removeById(@NonNull Integer id);

    public Optional<T> findById(@NonNull Integer id);

    public @NonNull Collection<T> findAll();

}
