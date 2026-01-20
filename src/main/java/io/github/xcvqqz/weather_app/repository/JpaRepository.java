package io.github.xcvqqz.weather_app.repository;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaRepository <T, K extends Serializable> extends Serializable {


    public void save(T t);
    public List<T> findAll();
    public void deleteById(K id);
    public void deleteAll();
    public Optional<T> findById(K id);



}