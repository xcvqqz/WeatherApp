package io.github.xcvqqz.weather_app.service;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Transactional
    public void save(Location location){
        locationRepository.save(location);
    }

    @Transactional(readOnly = true)
    public List<Location> findAll(){
        return locationRepository.findAll();
    }


    @Transactional(readOnly = true)
    public Location findById(Long id){
        return locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Неверный Id локации"));
    }

    @Transactional
    public void delete(Location location){
        locationRepository.delete(location);
    }

}