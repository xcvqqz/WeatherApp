package io.github.xcvqqz.weather_app.repository;


import io.github.xcvqqz.weather_app.entity.Location;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface JpaRepository <T, K extends Serializable> extends Serializable {


    public void save(T t);



    public List<T> findAll();
    public Optional<T> findById(K id);
    public Optional<T> findByName(String name);



    public void deleteById(K id);
    public void deleteAll();
    public void deleteByName(String name);

}