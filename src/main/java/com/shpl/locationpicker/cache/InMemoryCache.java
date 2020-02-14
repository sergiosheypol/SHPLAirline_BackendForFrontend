package com.shpl.locationpicker.cache;

import com.github.benmanes.caffeine.cache.Cache;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

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

    public Optional<V> get(K key) {
        return Optional.ofNullable(cache.getIfPresent(key));
    }

    public Optional<V> push(K key, V value) {
        return Optional.ofNullable(cache.get(key, k -> {
            log.info("Value with key '" + key + "' is not cached");
            return value;
        }));
    }
}
