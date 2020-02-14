package com.shpl.locationpicker.provider;

import com.shpl.locationpicker.model.Airport;
import com.shpl.locationpicker.model.Autocomplete;
import com.shpl.locationpicker.model.Country;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(DataProperties.class)
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

}
