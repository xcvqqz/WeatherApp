package io.github.xcvqqz.weather_app.repository;


import java.util.List;

public interface JpaRepository <T> {

    public void save(T t);



    public List<T> findAll();
    public T findById(Long id);
    public T findByName(String name);



    public void deleteById(T id);
    public void deleteAll();
    public void deleteByName(T name);

}