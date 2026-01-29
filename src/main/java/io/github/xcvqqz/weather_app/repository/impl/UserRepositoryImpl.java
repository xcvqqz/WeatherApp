package io.github.xcvqqz.weather_app.repository.impl;

import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.AbstractRepositoryImpl;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public class UserRepositoryImpl extends AbstractRepositoryImpl<User, Long> implements UserRepository {


    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        super(sessionFactory,  User.class);
    }


    @Override
    public Optional<User> findByLogin(String login) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("FROM User WHERE login = :login")
                .setParameter("login", login)
                .uniqueResultOptional();
    }
}