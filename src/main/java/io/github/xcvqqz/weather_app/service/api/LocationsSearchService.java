package io.github.xcvqqz.weather_app.service.api;


import io.github.xcvqqz.weather_app.dto.api.request.ApiLocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.api.response.ApiLocationsResponseDTO;
import io.github.xcvqqz.weather_app.exception.LocationsNotFoundException;
import io.github.xcvqqz.weather_app.exception.WeatherApiCommunicationException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class LocationsSearchService {

    private static final String LOCATION_NOT_FOUND = "No location named %s was found for your request. " +
            "Such a location does not exist. Please check the location name and enter it again";

    private static final String WEATHER_API_CONNECTION_ERROR = "Failed to receive a response from the API, probably due to connection loss";

    private final RestTemplate restTemplate;
    private final GeocodingUriBuilder geocodingUriBuilder;

    public List<ApiLocationsResponseDTO> findAll(ApiLocationsRequestDTO locationsRequestDTO) {
        String searchLocation = locationsRequestDTO.location();
        List<ApiLocationsResponseDTO> locations = fetchLocationsFromApi(searchLocation);

        if(locations.isEmpty()){
            throw new LocationsNotFoundException(String.format(LOCATION_NOT_FOUND, searchLocation));
        }

        log.info("Successfully fetched Location, size: {}", locations.size());
        return locations;
    }

    private List<ApiLocationsResponseDTO> fetchLocationsFromApi(String location){

        URI uri = geocodingUriBuilder.buildLocationSearchUri(location);

        try {
            ResponseEntity<List<ApiLocationsResponseDTO>> response = restTemplate.exchange(
                    uri,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>() {
                    }
            );
            return response.getBody();
        } catch (ResourceAccessException e){
            log.error("No response from the API. Connection lost: {}", e.getMessage());
            throw new WeatherApiCommunicationException(WEATHER_API_CONNECTION_ERROR);
        }
    }
}