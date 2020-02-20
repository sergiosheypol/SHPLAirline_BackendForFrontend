package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.Country;
import com.shpl.locations.provider.DataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
public class CountriesService extends ServiceTemplate<Country> {
    
    public CountriesService(final DataProvider dataProvider, final InMemoryCache<String, Country> inMemoryCache) {
        super(dataProvider, inMemoryCache);
    }

    @Override
    Flux<Country> getDataFromProvider() {
        return super.getDataProvider().getCountries();
    }

    @Override
    Country getEmptyObject() {
        return Country.builder().build();
    }

    @Override
    String getCacheCode(final Country country) {
        return country.getCode();
    }
}
