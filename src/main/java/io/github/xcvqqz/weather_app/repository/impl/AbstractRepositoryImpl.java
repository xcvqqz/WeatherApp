package io.github.xcvqqz.weather_app.repository.impl;

import io.github.xcvqqz.weather_app.repository.AbstractRepository;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


public abstract class AbstractRepositoryImpl <T, K> implements AbstractRepository<T, K> {

    private static final String DELETE_BY_ID_QUERY = "DELETE FROM %s WHERE id = :id";
    private static final String FIND_ALL_QUERY = "FROM %s";
    private static final String DELETE_ALL_QUERY = "DELETE FROM %s";

    protected final SessionFactory sessionFactory;
    protected final Class<T> entity;
    protected final String entityName;

    public AbstractRepositoryImpl(SessionFactory sessionFactory, Class<T> entity) {
        this.entity = entity;
        this.sessionFactory = sessionFactory;
        this.entityName = entity.getSimpleName();
    }


    @Override
    public Optional<T> save(T entity) {
        getCurrentSession().persist(entity);
        sessionFactory.getCurrentSession().flush();
        return Optional.ofNullable(entity);
    }

    @Override
    public void deleteById(K id) {
                 getCurrentSession()
                .createQuery(String.format(DELETE_BY_ID_QUERY, entityName))
                .setParameter("id", id)
                .executeUpdate();
    }


    @Override
    public List<T> findAll(){
        return   getCurrentSession()
                .createQuery(String.format(FIND_ALL_QUERY, entityName)).list();
    }

    @Override
    public Optional<T> findById(K id){
        return Optional.ofNullable(getCurrentSession().find(entity, id));
    }

    @Override
    public void deleteAll(){
        getCurrentSession().createQuery(String.format(DELETE_ALL_QUERY, entityName)).executeUpdate();
    }

    @Override
    public void delete(T entity){
        getCurrentSession().remove(entity);
    }


    protected Session getCurrentSession(){
        return sessionFactory.getCurrentSession();
    }

}