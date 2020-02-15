package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.City;
import com.shpl.locations.provider.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitiesService {
    private final DataProvider dataProvider;
    private final InMemoryCache<String, City> cityInMemoryCache;

    public List<City> getCities() {
        return dataProvider.getCities().collectList().block();
    }

    public City getCity(final String code) {
        return cityInMemoryCache.get(code)
                .orElse(cityInMemoryCache
                        .push(code, dataProvider.getCity(code).block())
                        .orElse(City.builder().build())
                );
    }
}
