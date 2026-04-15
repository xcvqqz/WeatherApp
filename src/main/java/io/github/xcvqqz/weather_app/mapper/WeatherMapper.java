//package io.github.xcvqqz.weather_app.mapper;


//
//@Mapper(componentModel = "spring")
//public interface WeatherMapper {
//
//
//    @Mapping(source = "coord.lon", target = "lon")
//    @Mapping(source = "coord.lat", target = "lat")
//    @Mapping(source = "weather", target = "description")
//    @Mapping(source = "main.temp", target = "temp")
//    @Mapping(source = "main.feelsLike", target = "feelsLike")
//    @Mapping(source = "main.humidity", target = "humidity")
//    @Mapping(source = "sys.country", target = "country")
//    @Mapping(target = "city", source = "geoLocation.name")
//    WeatherData toWeatherData(LocationsResponseDTO weatherResponseDTO);


//    default String convertWeatherList(List<LocationsResponseDTO.Weather> weather) {
//        return weather != null && !weather.isEmpty() ? weather.get(0).getDescription() : null;
//    }

//}
