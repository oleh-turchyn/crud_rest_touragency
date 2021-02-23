package com.turchyn.lab2.repository;

import com.turchyn.lab2.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByName(String name);
}
