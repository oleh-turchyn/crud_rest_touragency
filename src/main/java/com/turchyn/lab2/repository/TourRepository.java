package com.turchyn.lab2.repository;

import com.turchyn.lab2.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourRepository extends JpaRepository<Tour,Integer> {
    public Optional<Tour> findByTitle(String title);
}
