package com.turchyn.lab2.service.impl;

import com.turchyn.lab2.model.Tour;
import com.turchyn.lab2.repository.TourRepository;
import com.turchyn.lab2.service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class TourServiceImpl implements TourService {

    TourRepository tourRepository;

    @Autowired
    public TourServiceImpl(TourRepository tourRepository){
        this.tourRepository=tourRepository;
    }

    @Override
    public Optional<Tour> findByTitle(String title) {
        return tourRepository.findByTitle(title);
    }

    @Override
    public Tour save(Tour tour) {
        return tourRepository.save(tour);
    }

    @Override
    public void removeById(Integer id) {
        tourRepository.deleteById(id);
    }

    @Override
    public Optional<Tour> findById(Integer id) {
        return tourRepository.findById(id);
    }

    @Override
    public Collection<Tour> findAll() {
        return tourRepository.findAll();
    }
}
