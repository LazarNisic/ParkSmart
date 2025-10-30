package com.lazarnisic.ParkSmart.configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

// Ova konfiguracija ima prioritet u odnosu na onu u application.yaml
@Configuration
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {

        CaffeineCache cityByName = new CaffeineCache(
                "cityByName",
                Caffeine.newBuilder()
                        .maximumSize(10_000)
                        .expireAfterWrite(Duration.ofMinutes(10))
                        .build()
        );

        CaffeineCache cityById = new CaffeineCache(
                "cityById",
                Caffeine.newBuilder()
                        .maximumSize(10_000)
                        .expireAfterWrite(Duration.ofMinutes(10))
                        .build()
        );


//        CaffeineCache citiesPage = new CaffeineCache(
//                "citiesPage",
//                Caffeine.newBuilder()
//                        .maximumSize(1_000)
//                        .expireAfterWrite(Duration.ofSeconds(60))
//                        .build()
//        );


        CaffeineCache reservationsByOwner = new CaffeineCache(
                "reservationsByOwner",
                Caffeine.newBuilder()
                        .maximumSize(5_000)
                        .expireAfterWrite(Duration.ofSeconds(90))
                        .build()
        );

        CaffeineCache reservationsByCreator = new CaffeineCache(
                "reservationsByCreator",
                Caffeine.newBuilder()
                        .maximumSize(5_000)
                        .expireAfterWrite(Duration.ofSeconds(90))
                        .build()
        );


        CaffeineCache reviewsByParkingSpotPage = new CaffeineCache(
                "reviewsByParkingSpotPage",
                Caffeine.newBuilder()
                        .maximumSize(2_000)
                        .expireAfterWrite(Duration.ofSeconds(60))
                        .build()
        );

        CaffeineCache reviewsByOwnerPage = new CaffeineCache(
                "reviewsByOwnerPage",
                Caffeine.newBuilder()
                        .maximumSize(2_000)
                        .expireAfterWrite(Duration.ofSeconds(60))
                        .build()
        );

        CaffeineCache avgRatingByParkingSpot = new CaffeineCache(
                "avgRatingByParkingSpot",
                Caffeine.newBuilder()
                        .maximumSize(10_000)
                        .expireAfterWrite(Duration.ofMinutes(10))
                        .build()
        );

        SimpleCacheManager scm = new SimpleCacheManager();
        scm.setCaches(List.of(
                cityByName,
                cityById,
                // citiesPage,
                reservationsByOwner,
                reservationsByCreator,
                reviewsByParkingSpotPage,
                reviewsByOwnerPage,
                avgRatingByParkingSpot

        ));
        return scm;
    }
}
