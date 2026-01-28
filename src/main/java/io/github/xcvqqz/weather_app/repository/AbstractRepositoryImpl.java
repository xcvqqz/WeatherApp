package io.github.xcvqqz.weather_app.repository;


import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;


//Т - тип сущности, К - тип ключа
public abstract class AbstractRepositoryImpl <T, K> implements AbstractRepository<T, K> {


    protected final SessionFactory sessionFactory;
    private final Class<T> entity;
    private final String entityName;

    public AbstractRepositoryImpl(SessionFactory sessionFactory, Class<T> entity) {
        this.entity = entity;
        this.entityName = entity.getSimpleName();
        this.sessionFactory = sessionFactory;
    }


    @Override
    public T save(T entity) {
        sessionFactory.getCurrentSession().persist(entity);
        sessionFactory.getCurrentSession().flush();
        return entity;
    }


    @Override
    public void deleteById(K id) {
        sessionFactory.getCurrentSession().remove(id);
    }

    @Override
    public List<T> findAll(){       //через форматтер
        return sessionFactory
                .getCurrentSession()
                .createQuery(String.format("FROM %s", entityName)).list();
    }

    @Override
    public Optional<T> findById(K id){
        return Optional.ofNullable(sessionFactory.getCurrentSession().find(entity, id));
    }

    @Override
    public void deleteAll(){
        sessionFactory.getCurrentSession().createQuery(String.format("DELETE FROM %s", entityName)).executeUpdate();
    }

    @Override
    public void delete(T entity){
        sessionFactory.getCurrentSession().remove(entity);
    }





    //    @Override
//    public Optional<User> find(String login) {
//        return sessionFactory.getCurrentSession()
//                .createQuery("FROM User u WHERE u.login = :login", User.class)
//                .setParameter("login", login)
//                .uniqueResultOptional();
//    }


}