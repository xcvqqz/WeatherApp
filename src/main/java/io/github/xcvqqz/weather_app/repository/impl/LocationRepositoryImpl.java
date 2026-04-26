package io.github.xcvqqz.weather_app.repository.impl;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.LocationRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public class LocationRepositoryImpl extends AbstractRepositoryImpl<Location, Long> implements LocationRepository {

    private static final String FIND_LOCATIONS_BY_USER =
            "SELECT l FROM Location l " +
            "JOIN FETCH l.user " +
            "WHERE l.user.id = :userId";


    @Autowired
    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Location.class);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Location> findAllByUser(User user) {
        return getCurrentSession()
                .createQuery(FIND_LOCATIONS_BY_USER, Location.class)
                .setParameter("userId", user.getId())
                .getResultList();
    }

}