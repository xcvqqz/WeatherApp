package io.github.xcvqqz.weather_app.repository;


import io.github.xcvqqz.weather_app.entity.Location;
import io.github.xcvqqz.weather_app.entity.User;

import java.util.List;

public interface LocationRepository extends AbstractRepository <Location, Long>{

    List<Location> findAllByUser(User user);

}
