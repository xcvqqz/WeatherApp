package io.github.xcvqqz.weather_app.repository;

import io.github.xcvqqz.weather_app.entity.User;

import java.util.Optional;

public interface UserRepository extends AbstractRepository<User, Long> {

    public Optional<User> findByLogin(String login);

}