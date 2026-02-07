package io.github.xcvqqz.weather_app.repository.impl;

import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.AbstractRepositoryImpl;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class SessionRepositoryImpl extends AbstractRepositoryImpl<Session, UUID> implements SessionRepository {

    @Autowired
    public SessionRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory,  Session.class);
    }

}
