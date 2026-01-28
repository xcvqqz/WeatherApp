package io.github.xcvqqz.weather_app.repository;

import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, Long> {



}