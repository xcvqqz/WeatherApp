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

    private org.hibernate.Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(Session entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public List<Session> findAll() {
        return getCurrentSession()
                .createQuery("FROM Session", Session.class)
                .getResultList();
    }

    @Override
    public Optional<Session> findById(UUID sessionId) {
        return Optional.ofNullable(
                getCurrentSession()
                        .get(Session.class, sessionId));
    }



    @Override
    public void deleteById(UUID sessionId) {
        getCurrentSession().remove(getCurrentSession().get(Session.class, sessionId));
    }

    @Override
    public void deleteAll() {
        getCurrentSession().createQuery("delete from Session").executeUpdate();
    }


}
