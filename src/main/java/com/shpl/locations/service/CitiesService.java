package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.City;
import com.shpl.locations.provider.DataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class CitiesService extends ServiceTemplate<City> {
    public CitiesService(final DataProvider dataProvider, final InMemoryCache<String, City> cityInMemoryCache) {
        super(dataProvider, cityInMemoryCache);
    }

    @Override
    ArrayList<City> getItemsFromProvider() {
        return (ArrayList<City>) super.getDataProvider().getCities()
                .doOnEach(citySignal ->
                        ofNullable(citySignal.get()).ifPresent(this::pushItemToCache))
                .collectList()
                .block();
    }

    @Override
    void pushItemToCache(final City city) {
        super.getInMemoryCache().push(city.getCode(), city);
    }

    @Override
    City populateCacheAndFind(final String code) {
        log.info("Value for key '" + code + "' was not cached");
        getAll();
        return super.getInMemoryCache().get(code).orElse(City.builder().build());
    }
}
