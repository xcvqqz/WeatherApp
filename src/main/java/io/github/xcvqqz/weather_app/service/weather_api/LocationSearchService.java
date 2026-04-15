package io.github.xcvqqz.weather_app.service.weather_api;


import io.github.xcvqqz.weather_app.dto.locations.LocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.locations.LocationsResponseDTO;
import io.github.xcvqqz.weather_app.exception.LocationsNotFoundException;
import io.github.xcvqqz.weather_app.exception.WeatherApiCommunicationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@PropertySource("classpath:application-dev.properties")
public class LocationSearchService {

    private final RestTemplate restTemplate;
    private final GeocodingUriBuilder geocodingUriBuilder;


    public List<LocationsResponseDTO> getFoundLocations(LocationsRequestDTO locationsRequestDTO) {
        String searchLocation = locationsRequestDTO.location();
        List<LocationsResponseDTO> locations = fetchLocationsFromApi(searchLocation);
        log.info("Successfully fetched Location, size: {}", locations.size());
        return locations;
    }


    private List<LocationsResponseDTO> fetchLocationsFromApi(String location){

        URI uri = geocodingUriBuilder.buildLocationSearchUri(location);

        try {
            ResponseEntity<List<LocationsResponseDTO>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<LocationsResponseDTO>>(){}
            );
            return response.getBody();

        } catch (ResourceAccessException e){
            throw new WeatherApiCommunicationException(e.getMessage());
        } catch (HttpClientErrorException.NotFound e){
            throw new LocationsNotFoundException(e.getMessage());
        }
    }
}