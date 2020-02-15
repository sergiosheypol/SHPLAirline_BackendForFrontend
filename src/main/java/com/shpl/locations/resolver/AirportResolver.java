package com.shpl.locations.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shpl.locations.model.Airport;
import com.shpl.locations.model.Coordinates;
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
