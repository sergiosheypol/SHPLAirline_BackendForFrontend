package com.shpl.locations.service;

import com.shpl.locations.cache.InMemoryCache;
import com.shpl.locations.provider.DataProvider;
import io.vavr.control.Option;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Getter
@RequiredArgsConstructor
public abstract class ServiceTemplate<V> {
    private final DataProvider dataProvider;
    private final InMemoryCache<String, V> inMemoryCache;

    public List<V> getAll() {
        return Option.of(inMemoryCache.getCacheMap())
                .filter(map -> !map.isEmpty())
                .map(Map::values)
                .map(ArrayList::new)
                .getOrElse(this::getItemsFromProvider);
    }

    public V getOne(final String code) {
        return inMemoryCache
                .get(code)
                .orElseGet(() -> populateCacheAndFind(code));
    }

    abstract ArrayList<V> getItemsFromProvider();

    abstract void pushItemToCache(V v);

    abstract V populateCacheAndFind(String code);

}
