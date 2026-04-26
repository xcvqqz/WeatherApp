package io.github.xcvqqz.weather_app.repository.impl;

import io.github.xcvqqz.weather_app.entity.*;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.UUID;



@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User, Long> implements UserRepository {

    private static final String FIND_BY_LOGIN_QUERY = "FROM User WHERE login = :login";
    private static final String  FIND_BY_SESSION_ID_QUERY = "SELECT s.user FROM Session AS s WHERE s.id = :sessionId";

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory,  User.class);
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return  getCurrentSession()
                .createQuery(FIND_BY_LOGIN_QUERY)
                .setParameter("login", login)
                .uniqueResultOptional();
    }

    @Override
    public Optional<User> findBySessionId(UUID sessionId) {
        return Optional.ofNullable(getCurrentSession()
                .createQuery(FIND_BY_SESSION_ID_QUERY, User.class)
                .setParameter("sessionId", sessionId)
                .getSingleResultOrNull());
    }


}