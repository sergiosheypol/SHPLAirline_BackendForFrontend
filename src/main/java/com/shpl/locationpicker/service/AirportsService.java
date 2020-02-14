package com.shpl.locationpicker.service;

import com.shpl.locationpicker.model.Airport;
import com.shpl.locationpicker.provider.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
//TODO Implement caching system
public class AirportsService {
    private final DataProvider dataProvider;

    public List<Airport> getAirports() {
        return dataProvider.getAirports().collectList().block();
    }

    public Airport getAirport(final String iataCode) {
        return dataProvider.getAirport(iataCode).block();
    }

}
