package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.Airport;
import com.shpl.locations.provider.DataProvider;
import io.vavr.control.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class AirportsService {

    private final DataProvider dataProvider;
    private final InMemoryCache<String, Airport> airportInMemoryCache;



    public List<Airport> getAirports() {
        Option.of(airportInMemoryCache.getCacheMap())
                .filter(Map::isEmpty)
                .forEach(cacheMap -> new ArrayList<>(cacheMap.values()));

        return dataProvider.getAirports().map(this::pushAirportToCache).collectList().block();
    }

    public Airport getAirport(final String iataCode) {
        return airportInMemoryCache
                .get(iataCode)
                .orElseGet(() -> populateCacheAndFind(iataCode));
    }


    private Airport pushAirportToCache(Airport airport) {
        return airportInMemoryCache
                .get(airport.getIataCode())
                .orElse(airportInMemoryCache
                        .push(airport.getIataCode(), airport)
                        .orElse(Airport.builder().build()));
    }

    private Airport populateCacheAndFind(String iataCode) {
        log.info("Value for key '" + iataCode + "' was not cached");
        getAirports();
        return airportInMemoryCache.get(iataCode).orElse(Airport.builder().build());
    }
}
