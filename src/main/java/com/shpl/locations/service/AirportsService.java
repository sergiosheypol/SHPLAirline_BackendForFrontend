package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.Airport;
import com.shpl.locations.provider.DataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class AirportsService extends ServiceTemplate<Airport> {
    public AirportsService(final DataProvider dataProvider, final InMemoryCache<String, Airport> inMemoryCache) {
        super(dataProvider, inMemoryCache);
    }

    @Override
    ArrayList<Airport> getItemsFromProvider() {
        return (ArrayList<Airport>) super.getDataProvider().getAirports()
                .doOnEach(airportSignal ->
                        ofNullable(airportSignal.get()).ifPresent(this::pushItemToCache))
                .collectList()
                .block();
    }

    @Override
    void pushItemToCache(final Airport airport) {
        super.getInMemoryCache().push(airport.getIataCode(), airport);
    }

    @Override
    Airport populateCacheAndFind(final String code) {
        log.info("Value for key '" + code + "' is not cached");
        getAll();
        return super.getInMemoryCache().get(code).orElse(Airport.builder().build());
    }

}
