package com.shpl.locationpicker.service;

import com.shpl.locationpicker.model.Airport;
import com.shpl.locationpicker.provider.AirportProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportsService {
    private final AirportProvider airportProvider;

    public Flux<Airport> getAirports() {
        return airportProvider.getAirports();
    }

    public Mono<Airport> getAirport(final String iataCode) {
        return airportProvider.getAirport(iataCode);
    }
}
