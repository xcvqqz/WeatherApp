package io.github.xcvqqz.weather_app.service.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.xcvqqz.weather_app.dto.api.request.ApiLocationsRequestDTO;
import io.github.xcvqqz.weather_app.dto.api.response.ApiLocationsResponseDTO;
import io.github.xcvqqz.weather_app.exception.LocationsNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationsSearchServiceTest {

    private static final String LOCATIONS_RESPONSE_FROM_EXTERNAL_API =  """
            [
              {
                "name": "Klin",
                "local_names": {
                  "ru": "Клин",
                  "sr": "Клин",
                  "fi": "Klin",
                  "fr": "Kline",
                  "de": "Klin",
                  "ja": "クリヌ",
                  "uk": "Клін",
                  "et": "Klin",
                  "en": "Klin"
                },
                "lat": 56.3355601,
                "lon": 36.7351318,
                "country": "RU",
                "state": "Moscow Oblast"
              },
              {
                "name": "Klin",
                "local_names": {
                  "sk": "Klin",
                  "hu": "Klin"
                },
                "lat": 49.4350714,
                "lon": 19.4971363,
                "country": "SK",
                "state": "Region of Žilina"
              },
              {
                "name": "Klin",
                "local_names": {
                  "pl": "Klin"
                },
                "lat": 50.5071603,
                "lon": 20.681561,
                "country": "PL",
                "state": "Świętokrzyskie Voivodeship"
              },
              {
                "name": "Klin",
                "local_names": {
                  "pl": "Klin"
                },
                "lat": 50.4913889,
                "lon": 20.7972222,
                "country": "PL",
                "state": "Świętokrzyskie Voivodeship"
              },
              {
                "name": "Klin",
                "lat": 53.2688825,
                "lon": 23.5663533,
                "country": "PL",
                "state": "Podlaskie Voivodeship"
              }
            ]
        """;

    private static final String GEO_URL = "https://api.openweathermap.org/geo/1.0/direct?q=Klin&limit=5&appid=testKey";
    private static final String GEO_URL_UNKNOWN = "https://api.openweathermap.org/geo/1.0/direct?q=xyzxyzxyzxyzxyxyz&limit=5&appid=testKey";

    private final ApiLocationsRequestDTO locationsRequestTest = new ApiLocationsRequestDTO("Klin");
    private final ApiLocationsRequestDTO unknownLocationRequestTest = new ApiLocationsRequestDTO("xyzxyzxyzxyzxyxyz");

    @Mock
    private GeocodingUriBuilder geocodingUriBuilder;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private LocationsSearchService locationsSearchService;

    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void shouldSuccessfullyFetchLocationsFromExternalApi(){

        List<ApiLocationsResponseDTO> expectedLocations = convertApiResponse();

        when(geocodingUriBuilder.buildLocationSearchUri(locationsRequestTest.location()))
                .thenReturn(URI.create(GEO_URL));

        when(restTemplate.exchange(
                eq(URI.create(GEO_URL)),
                eq(HttpMethod.GET),
                isNull(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(expectedLocations));

        List<ApiLocationsResponseDTO> result = locationsSearchService.findAll(locationsRequestTest);

        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(5, result.size());
        Assertions.assertEquals("Klin", result.get(0).name());
        Assertions.assertEquals("RU", result.get(0).country());
        Assertions.assertEquals("Moscow Oblast", result.get(0).state());

    }

    @Test
    public void shouldThrowLocationNotFoundExceptionWhenApiReturnsEmptyResult(){

        List<ApiLocationsResponseDTO> emptyResult = Collections.emptyList();

        when(geocodingUriBuilder.buildLocationSearchUri(unknownLocationRequestTest.location()))
                .thenReturn(URI.create(GEO_URL_UNKNOWN));

        when(restTemplate.exchange(
                eq(URI.create(GEO_URL_UNKNOWN)),
                eq(HttpMethod.GET),
                any(),
                any(ParameterizedTypeReference.class)
        )).thenReturn(ResponseEntity.ok(emptyResult));

        assertThrows(LocationsNotFoundException.class,
                () -> locationsSearchService.findAll(unknownLocationRequestTest));
    }

    private ArrayList<ApiLocationsResponseDTO> convertApiResponse(){

        try {
            return mapper.readValue(
                    LocationsSearchServiceTest.LOCATIONS_RESPONSE_FROM_EXTERNAL_API,
                    mapper.getTypeFactory().constructCollectionType(List.class, ApiLocationsResponseDTO.class)
            );
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}