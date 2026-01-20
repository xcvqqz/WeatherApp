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

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void save(User user) {
        getCurrentSession().persist(user);
    }

    @Override
    public List<User> findAll() {
        return getCurrentSession()
                .createQuery("FROM User", User.class)
                .getResultList();
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(
                getCurrentSession()
                        .get(User.class, id));
    }


    @Override
    public void deleteById(Long id) {
        User user = getCurrentSession().get(User.class, id);
        if (user != null) {
            getCurrentSession().remove(user);
        }

        //или ввыбрасываем своё исключение
    }

    @Override
    public Optional<User> findByLogin(String login) {
        return getCurrentSession()
                .createQuery("FROM User u WHERE u.login = :login", User.class)
                .setParameter("login", login)
                .uniqueResultOptional();
    }

    @Override
    public void deleteAll() {
        getCurrentSession().createQuery("delete from User").executeUpdate();
    }

}