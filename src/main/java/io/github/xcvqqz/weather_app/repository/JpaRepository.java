package io.github.xcvqqz.weather_app.repository;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.Session;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


//Т - тип сущности, К - тип ключа
public interface JpaRepository <T, K> {

    public T save(T entity);

    public void delete(T t);

    public List<T> findAll();

    public Optional<T> findById(K id);


    public void deleteById(K id);

   public void deleteAll();

}