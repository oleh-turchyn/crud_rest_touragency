package com.turchyn.lab2.controller;

import com.turchyn.lab2.exception.UserNotFoundException;
import com.turchyn.lab2.model.User;
import com.turchyn.lab2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:9191")
@RestController
@RequestMapping("/api")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    ResponseEntity<List<User>> findAllUsers() {
        try {
            List<User> users = new ArrayList<>();
            users.addAll(userService.findAll());

            if (users.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/userById/{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id") int id) {
        Optional<User> user = Optional.ofNullable(userService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with " + id + " is Not Found!")));
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/userByName/{name}")
    public ResponseEntity<List<User>> findUserByName(@PathVariable("name") String name) {
        try {
            List<User> usersWithName = userService.findAll().stream()
                    .filter(user -> user.getName().equals(name))
                    .collect(Collectors.toList());
            if (usersWithName.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(usersWithName, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        try {
            User createdUser = userService
                    .save(user);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable("id") int id, @RequestBody User user) {
        Optional<User> userData = Optional.ofNullable(userService
                .findById(id).orElseThrow(() -> new UserNotFoundException("User with " + id + " is Not Found!")));

        if (userData.isPresent()) {
            User updatedUser = userData.get();
            updatedUser.setName(user.getName());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setEmail(user.getEmail());
            return new ResponseEntity<>(userService.save(updatedUser), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> removeUser(@PathVariable("id") int id) {
        try {
            User user = userService.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("User with " + id + " is Not Found!"));
            userService.removeById(user.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
