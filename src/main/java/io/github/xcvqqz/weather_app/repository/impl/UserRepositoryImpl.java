package io.github.xcvqqz.weather_app.repository.impl;

import io.github.xcvqqz.weather_app.entity.User;
import io.github.xcvqqz.weather_app.repository.UserRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {


    private final SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }




//
//    @Override
//    public Optional<User> find(String login) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("FROM User u WHERE u.login = :login", User.class)
//                .setParameter("login", login)
//                .uniqueResultOptional();
//    }


}