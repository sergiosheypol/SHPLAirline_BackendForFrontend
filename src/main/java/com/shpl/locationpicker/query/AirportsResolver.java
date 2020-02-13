package com.shpl.locationpicker.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shpl.locationpicker.model.Airport;
import com.shpl.locationpicker.service.AirportsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AirportsResolver implements GraphQLQueryResolver {
    private final AirportsService airportsService;

    public List<Airport> getAirports() {
        return airportsService.getAirports().collectList().block();
    }

}
