package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.Country;
import com.shpl.locations.provider.DataProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import static java.util.Optional.ofNullable;

@Slf4j
@Service
public class CountriesService extends ServiceTemplate<Country> {
    
    public CountriesService(final DataProvider dataProvider, final InMemoryCache<String, Country> inMemoryCache) {
        super(dataProvider, inMemoryCache);
    }

    @Override
    ArrayList<Country> getItemsFromProvider() {
        return (ArrayList<Country>) super.getDataProvider().getCountries()
                .doOnEach(countrySignal ->
                        ofNullable(countrySignal.get()).ifPresent(this::pushItemToCache))
                .collectList()
                .block();
    }

    @Override
    void pushItemToCache(final Country country) {
        super.getInMemoryCache().push(country.getCode(), country);
    }

    @Override
    Country populateCacheAndFind(final String code) {
        log.info("Value for key '" + code + "' was not cached");
        getAll();
        return super.getInMemoryCache().get(code).orElse(Country.builder().build());
    }
}
