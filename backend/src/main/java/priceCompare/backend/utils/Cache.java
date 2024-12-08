package priceCompare.backend.utils;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cache<K, V> {
    private LocalDateTime lastCleanup;
    private final Map<K, V> cache;
    private final Map<K, LocalDateTime> expiresAt;

    private final int MINUTES_BETWEEN_CLEANUP;
    private final int DEFAULT_ITEM_AGE_MINUTES;

    public Cache(int MINUTES_BETWEEN_CLEANUP, int MAX_ITEM_AGE_MINUTES) {
        this.MINUTES_BETWEEN_CLEANUP = MINUTES_BETWEEN_CLEANUP;
        this.DEFAULT_ITEM_AGE_MINUTES = MAX_ITEM_AGE_MINUTES;

        lastCleanup = LocalDateTime.now();
        cache = new HashMap<>();
        expiresAt = new HashMap<>();
    }

    private void cleanOldItems() {
        LocalDateTime currTime = LocalDateTime.now();
        if(Duration.between(currTime, lastCleanup).toMinutes() < MINUTES_BETWEEN_CLEANUP)
            return;

        lastCleanup = LocalDateTime.now();

        for(var entryIterator = expiresAt.entrySet().iterator(); entryIterator.hasNext();) {
            Map.Entry<K, LocalDateTime> entry = entryIterator.next();
            if(Duration.between(currTime, entry.getValue()).isPositive())
                continue;

            cache.remove(entry.getKey());
            entryIterator.remove();
        }
    }

    public Duration timeUntilItemCleanup(K key) {
        synchronized (this) {
            cleanOldItems();
            if (!cache.containsKey(key)) return Duration.of(0, ChronoUnit.SECONDS);

            return Duration.between(LocalDateTime.now(), expiresAt.get(key));
        }
    }

    public void put(K key, V value) {
        synchronized (this) {
            cleanOldItems();

            cache.put(key, value);
            expiresAt.put(key, LocalDateTime.now().plusMinutes(DEFAULT_ITEM_AGE_MINUTES));
        }
    }

    public void put(K key, V value, Duration cacheFor) {
        synchronized (this) {
            cleanOldItems();

            cache.put(key, value);
            expiresAt.put(key, LocalDateTime.now().plus(cacheFor));
        }
    }

    public V get(K key) {
        synchronized (this) {
            cleanOldItems();

            return cache.get(key);
        }
    }

    public void remove(K key) {
        synchronized (this) {
            cache.remove(key);
            expiresAt.remove(key);
        }
    }

    public Set<K> keySet() {
        synchronized (this) {
            return new HashSet<>(cache.keySet());
        }
    }
}
