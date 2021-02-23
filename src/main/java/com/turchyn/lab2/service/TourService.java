package com.turchyn.lab2.service;

import com.turchyn.lab2.model.Tour;

import java.util.Optional;

public interface TourService extends AbstractDomainObjectService<Tour> {
    public Optional<Tour> findByTitle(String title);
}
