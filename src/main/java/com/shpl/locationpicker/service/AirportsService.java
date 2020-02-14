package com.shpl.locationpicker.service;

import com.shpl.locationpicker.cache.InMemoryCache;
import com.shpl.locationpicker.model.Airport;
import com.shpl.locationpicker.provider.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AirportsService {

    private final DataProvider dataProvider;
    private final InMemoryCache<String, Airport> airportInMemoryCache;

    public List<Airport> getAirports() {
        return dataProvider.getAirports().collectList().block();
    }

    public Airport getAirport(final String iataCode) {
        return airportInMemoryCache.get(iataCode)
                .orElse(airportInMemoryCache
                        .push(iataCode, dataProvider.getAirport(iataCode).block())
                        .orElse(Airport.builder().build())
                );
    }

}
