package io.github.xcvqqz.weather_app.repository.impl;


import io.github.xcvqqz.weather_app.entity.Session;
import io.github.xcvqqz.weather_app.repository.SessionRepository;
//import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class SessionRepositoryImpl implements SessionRepository {


    private final SessionFactory sessionFactory;

    @Autowired
    public SessionRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

//    private Session getCurrentSession() {
//        return sessionFactory.getCurrentSession();
//    }


    @Override
    public void save(Session session) {
        sessionFactory.getCurrentSession().persist(session);
    }

    @Override
    public List<Session> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Session").list();
    }

    @Override
    public Optional<Session> findById(UUID sessionId) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Session.class, sessionId));
    }

    @Override
    public Optional<Session> findByName(String name) {
        return Optional.ofNullable(sessionFactory.getCurrentSession().get(Session.class, name));
    }

    @Override
    public void deleteById(UUID sessionId) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Session.class, sessionId));
    }

    @Override
    public void deleteAll() {
        sessionFactory.getCurrentSession().createQuery("delete from Session").executeUpdate();
    }

    @Override
    public void deleteByName(String name) {
        sessionFactory.getCurrentSession().delete(sessionFactory.getCurrentSession().get(Session.class, name));
    }

}
