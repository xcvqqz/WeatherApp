package io.github.xcvqqz.weather_app.repository;


import java.util.List;
import java.util.Optional;


//Т - тип сущности, К - тип ключа
public interface AbstractRepository<T, K> {

    public T save(T entity);

    public void delete(T t);

    public List<T> findAll();

    public Optional<T> findById(K id);

    public void deleteById(K id);

   public void deleteAll();


}