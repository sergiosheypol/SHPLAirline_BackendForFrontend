package com.shpl.locations.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;
import java.util.concurrent.ConcurrentMap;

@Slf4j
@RequiredArgsConstructor
public class InMemoryCache<K, V> {

    private final Cache<K, V> cache;
    private final boolean enabled;

    public void clear() {
        if (enabled) {
            log.info("Clearing in-memory cache");
            cache.cleanUp();
        }
    }

    public ConcurrentMap<K,V> getCacheMap() {
        log.info("Retrieving entire cache");
        return cache.asMap();
    }

    public Optional<V> get(K key) {
        log.info("Retrieving '" + key + "' from cache");
        return Optional.ofNullable(cache.getIfPresent(key));
    }

    public Optional<V> push(K key, V value) {
        return Optional.ofNullable(cache.get(key, k -> {
            log.info("Value with key '" + key + "' will be cached");
            return value;
        }));
    }


}
