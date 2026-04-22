package io.github.xcvqqz.weather_app.repository.impl;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.AbstractRepositoryImpl;
import io.github.xcvqqz.weather_app.repository.LocationRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Repository
public class LocationRepositoryImpl extends AbstractRepositoryImpl<Location, Long> implements LocationRepository {


    @Autowired
    public LocationRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory, Location.class);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Location> findAllByUser(User user) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("FROM Location WHERE user.id = :userId", Location.class)
//                .setParameter("userId", user.getId())
//                .getResultList();

        return sessionFactory.getCurrentSession()
                .createQuery(
                        "SELECT l FROM Location l JOIN FETCH l.user WHERE l.user.id = :userId",
                        Location.class
                )
                .setParameter("userId", user.getId())
                .getResultList();


    }

}