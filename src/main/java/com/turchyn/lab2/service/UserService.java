package com.turchyn.lab2.service;

import com.turchyn.lab2.model.User;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface UserService extends AbstractDomainObjectService<User>{
    public Optional<User> findByName(@NonNull String name);
}
