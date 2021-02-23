package com.turchyn.lab2.service.impl;

import com.turchyn.lab2.model.User;
import com.turchyn.lab2.repository.UserRepository;
import com.turchyn.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void removeById(Integer id) {
        userRepository.deleteById(id);
    }


    @Override
    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
