package io.github.xcvqqz.weather_app.repository.impl;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.repository.LocationRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class LocationRepositoryImpl implements LocationRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public void save(Location location) {
        getCurrentSession().save(location);
    }

    @Override
    public List<Location> findAll() {
        return getCurrentSession().createQuery("from Location").list();
    }

    @Override
    public Optional<Location> findById(Long id) {
        return Optional.ofNullable(getCurrentSession().get(Location.class, id));
    }

    @Override
    public Optional<Location> findByName(String name) {
        return Optional.ofNullable(getCurrentSession().get(Location.class, name));
    }

    @Override
    public void deleteById(Long id) {
        getCurrentSession().delete(getCurrentSession().get(Location.class, id));
    }

    @Override
    public void deleteAll() {
        getCurrentSession().createQuery("delete from Location").executeUpdate();
    }

    @Override
    public void deleteByName(String name) {
        getCurrentSession().delete(getCurrentSession().get(Location.class, name));
    }
}
