package com.turchyn.lab2.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tour_tb")
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String type;
    private String transport;
    private String nutrition;
    private int duration;
    private double price;

    @OneToMany(mappedBy = "tour", cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnore
    private Set<Order> orders = new HashSet<>();
}
