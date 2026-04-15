//package io.github.xcvqqz.weather_app.util;
//
//
//import io.github.xcvqqz.weather_app.dto.locations.LocationsRequestDTO;
//import io.github.xcvqqz.weather_app.exception.BadRequestException;
//
//public class Validator {
//
//    private static final String LOCATION_REQUEST_CANNOT_BE_NULL_OR_EMPTY =
//            "Location Request cannot be null or empty";
//
//    private static final String LOCATION_NAME_CANNOT_BE_NULL_OR_EMPTY =
//            "Location name cannot be null or empty";
//
//    private static final String LOCATION_NAME_IS_TOO_LONG =
//            "Location name is too long";
//
//    private static final String LOCATION_NAME_IS_TOO_SHORT =
//            "Location name is too short";
//
//    private Validator(){}
//
//
//
//    public static void validate(LocationsRequestDTO locationsRequest) {
//        if (locationsRequest == null) {
//            throw new BadRequestException(LOCATION_REQUEST_CANNOT_BE_NULL_OR_EMPTY);
//        }
//
//        String location = locationsRequest.location();
//
//        if (name == null || name.isBlank()) {
//            throw new ValidationException(MISSING_PARAMETER_MESSAGE + "name");
//        }
//        if (sign == null || sign.isBlank()) {
//            throw new ValidationException(MISSING_PARAMETER_MESSAGE + "sign");
//        }
//        validateCurrencyCode(code);
//    }
//
//
//
//
//       if (weatherRequestDTO == null){
//        throw new BadRequestException("Location Request cannot be null or empty");
//    }
//    String city = weatherRequestDTO.location();
//
//        if((city == null) || city.isEmpty()){
//        throw new BadRequestException("Location name cannot be null or empty");
//    }
//
//        if(city.length() > 100){
//        throw new BadRequestException("Location name is too long");
//    }
//
//
//
//}
