package io.github.xcvqqz.weather_app.repository;


import io.github.xcvqqz.weather_app.model.entity.Session;
import io.github.xcvqqz.weather_app.model.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends AbstractRepository<Session, UUID>  {

    public Optional<User> findUserById(UUID sessionId);

}
