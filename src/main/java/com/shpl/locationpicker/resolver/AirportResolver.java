package com.shpl.locationpicker.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shpl.locationpicker.model.Airport;
import com.shpl.locationpicker.model.Coordinates;
import org.springframework.stereotype.Component;

@Component
public class AirportResolver implements GraphQLResolver<Airport> {

    public Coordinates getCoordinates(Airport airport) {
        return Coordinates.builder()
                .latitude(airport.getCoordinates().get("latitude"))
                .longitude(airport.getCoordinates().get("longitude"))
                .build();
    }

}