package com.shpl.locations.provider;

import com.shpl.locations.model.Airport;
import com.shpl.locations.model.Autocomplete;
import com.shpl.locations.model.City;
import com.shpl.locations.model.Country;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@Service
@RequiredArgsConstructor
public class DataProvider {

    private final DataProperties dataProperties;
    private final WebClient webClient;

    public Flux<Airport> getAirports() {
        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path(dataProperties.getAirports()).build())
                .retrieve()
                .bodyToFlux(Airport.class);
    }

    public Mono<Airport> getAirport(final String iataCode) {
        return Mono.from(
                webClient.method(HttpMethod.GET)
                        .uri(uriBuilder -> uriBuilder.path(dataProperties.getAirports()).build())
                        .retrieve()
                        .bodyToFlux(Airport.class)
                        .filter(airport -> airport.getIataCode().equals(iataCode)));

    }

    public Flux<Country> getCountries() {
        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path(dataProperties.getCountries()).build())
                .retrieve()
                .bodyToFlux(Country.class);
    }

    public Mono<Country> getCountry(final String code) {
        return Mono.from(
                webClient.method(HttpMethod.GET)
                        .uri(uriBuilder -> uriBuilder.path(dataProperties.getCountries()).build())
                        .retrieve()
                        .bodyToFlux(Country.class)
                        .filter(country -> country.getCode().equals(code)));
    }

    public Flux<Autocomplete> getAutocomplete(final String phrase) {

        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(dataProperties.getAutocomplete())
                        .queryParam("phrase", phrase)
                        .build())
                .retrieve()
                .bodyToFlux(Autocomplete.class);
    }

    public Flux<City> getCities() {
        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path(dataProperties.getCities()).build())
                .retrieve()
                .bodyToFlux(City.class);
    }

    public Mono<City> getCity(final String code) {
        return Mono.from(
                webClient.method(HttpMethod.GET)
                        .uri(uriBuilder -> uriBuilder.path(dataProperties.getCities()).build())
                        .retrieve()
                        .bodyToFlux(City.class)
                        .filter(country -> country.getCode().equals(code)));
    }
}
