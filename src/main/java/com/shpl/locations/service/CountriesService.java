package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.Country;
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
public class CountriesService {

    private final DataProvider dataProvider;
    private final InMemoryCache<String, Country> countryInMemoryCache;

    public List<Country> getCountries() {

        Option.of(countryInMemoryCache.getCacheMap())
            .filter(Map::isEmpty)
            .forEach(cacheMap -> new ArrayList<>(cacheMap.values()));

        return dataProvider.getCountries().map(this::pushCountryToCache).collectList().block();

    }

    public Country getCountry(final String code) {
        return countryInMemoryCache
                .get(code)
                .orElseGet(() -> populateCacheAndFind(code));
    }

    private Country pushCountryToCache(Country country) {
        return countryInMemoryCache
                .get(country.getCode())
                .orElse(countryInMemoryCache
                        .push(country.getCode(), country)
                        .orElse(Country.builder().build()));
    }

    private Country populateCacheAndFind(String code) {
        log.info("Value for key '" + code + "' was not cached");
        getCountries();
        return countryInMemoryCache.get(code).orElse(Country.builder().build());
    }
}
