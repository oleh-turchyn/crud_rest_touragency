package com.turchyn.lab2.controller;

import com.turchyn.lab2.exception.UserNotFoundException;
import com.turchyn.lab2.model.Tour;
import com.turchyn.lab2.service.TourService;
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
public class TourController {

    TourService tourService;

    @Autowired
    public TourController(TourService tourService) {
        this.tourService = tourService;
    }

    @GetMapping("/tours")
    ResponseEntity<List<Tour>> findAllTours() {
        try {
            List<Tour> tours = new ArrayList<>(tourService.findAll());

            if (tours.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tours, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/tourById/{id}")
    public ResponseEntity<Tour> findTourById(@PathVariable("id") int id) {
        Optional<Tour> tour = Optional.ofNullable(tourService.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Tour with " + id + " is Not Found!")));
        return tour.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping(value = "/tourByTitle/{title}")
    public ResponseEntity<List<Tour>> findTourByTitle(@PathVariable("title") String title) {
        try {
            List<Tour> toursWithTitle = tourService.findAll().stream()
                    .filter(tour -> tour.getTitle().equals(title))
                    .collect(Collectors.toList());
            if (toursWithTitle.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(toursWithTitle, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tours")
    public ResponseEntity<Tour> saveTour(@RequestBody Tour tour) {
        try {
            Tour createdTour = tourService
                    .save(tour);
            return new ResponseEntity<>(createdTour, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/tours/{id}")
    public ResponseEntity<Tour> updateTour(@PathVariable("id") int id, @RequestBody Tour tour) {
        Optional<Tour> tourData = Optional.ofNullable(tourService
                .findById(id).orElseThrow(() -> new UserNotFoundException("Tour with " + id + " is Not Found!")));

        if (tourData.isPresent()) {
            Tour updatedTour = tourData.get();
            updatedTour.setTitle(tour.getTitle());
            updatedTour.setTransport(tour.getTransport());
            updatedTour.setNutrition(tour.getNutrition());
            updatedTour.setDuration(tour.getDuration());
            updatedTour.setPrice(tour.getPrice());
            return new ResponseEntity<>(tourService.save(updatedTour), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/tours/{id}")
    public ResponseEntity<HttpStatus> removeTour(@PathVariable("id") int id) {
        try {
            Tour tour = tourService.findById(id)
                    .orElseThrow(() -> new UserNotFoundException("Tour with " + id + " is Not Found!"));
            tourService.removeById(tour.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
