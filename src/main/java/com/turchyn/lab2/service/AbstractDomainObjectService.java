package com.turchyn.lab2.service;

import com.turchyn.lab2.model.DomainObject;
import org.springframework.lang.NonNull;

import java.util.Collection;

public interface AbstractDomainObjectService<T extends DomainObject> {
    public T save(@NonNull T object);

    public void remove(@NonNull T object);

    public T getById(@NonNull Long id);

    public @NonNull Collection<T> getAll();

}
