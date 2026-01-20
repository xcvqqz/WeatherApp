package io.github.xcvqqz.weather_app.repository;

import io.github.xcvqqz.weather_app.entity.Session;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {



    public Optional<Session> findById(UUID sessionId);


}
