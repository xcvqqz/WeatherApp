package io.github.xcvqqz.weather_app.repository;

import io.github.xcvqqz.weather_app.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends AbstractRepository<User, Long> {

    Optional<User> findByLogin(String login);

    Optional<User> findBySessionId(UUID id);

}