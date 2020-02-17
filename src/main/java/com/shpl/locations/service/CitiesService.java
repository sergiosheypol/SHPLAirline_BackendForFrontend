package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.model.City;
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
public class CitiesService {
    private final DataProvider dataProvider;
    private final InMemoryCache<String, City> cityInMemoryCache;

    public List<City> getCities() {
        Option.of(cityInMemoryCache.getCacheMap())
                .filter(Map::isEmpty)
                .forEach(cacheMap -> new ArrayList<>(cacheMap.values()));

        return dataProvider.getCities().map(this::pushCityToCache).collectList().block();
    }

    public City getCity(final String code) {
        return cityInMemoryCache
                .get(code)
                .orElseGet(() -> populateCacheAndFind(code));
    }

    private City pushCityToCache(City city) {
        return cityInMemoryCache
                .get(city.getCode())
                .orElse(cityInMemoryCache
                        .push(city.getCode(), city)
                        .orElse(City.builder().build()));
    }

    private City populateCacheAndFind(String code) {
        log.info("Value for key '" + code + "' was not cached");
        getCities();
        return cityInMemoryCache.get(code).orElse(City.builder().build());
    }
}
