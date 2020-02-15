package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.Country;
import com.shpl.locations.provider.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountriesService {

    private final DataProvider dataProvider;
    private final InMemoryCache<String, Country> countryInMemoryCache;

    public List<Country> getCountries() {
        return dataProvider.getCountries().collectList().block();
    }

    public Country getCountry(final String code) {
        return countryInMemoryCache.get(code)
                .orElse(countryInMemoryCache
                        .push(code, dataProvider.getCountry(code).block())
                        .orElse(Country.builder().build())
                );
    }
}
