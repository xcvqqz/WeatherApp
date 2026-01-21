package io.github.xcvqqz.weather_app.repository;

import io.github.xcvqqz.weather_app.entity.Location;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long>{


    public Optional<Location> findById(Long id);
    public void save(Location location);

}
