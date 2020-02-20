package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.City;
import com.shpl.locations.provider.DataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class CitiesService extends ServiceTemplate<City> {
    public CitiesService(final DataProvider dataProvider, final InMemoryCache<String, City> cityInMemoryCache) {
        super(dataProvider, cityInMemoryCache);
    }

    @Override
    Flux<City> getDataFromProvider() {
        return super.getDataProvider().getCities();
    }

    @Override
    City getEmptyObject() {
        return City.builder().build();
    }

    @Override
    String getCacheCode(final City city) {
        return city.getCode();
    }
}
