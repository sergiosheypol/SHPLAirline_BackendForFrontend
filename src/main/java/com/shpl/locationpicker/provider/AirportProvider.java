package com.shpl.locationpicker.provider;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shpl.locationpicker.model.Airport;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

@Data
@Service
@RequiredArgsConstructor
@EnableConfigurationProperties(AirportProperties.class)
public class AirportProvider  {

    private final AirportProperties airportProperties;
    private final WebClient webClient;

    public Flux<Airport> getAirports() {

        URI uri = URI.create(airportProperties.getEndpoint());

        return webClient.method(HttpMethod.GET).uri(uri).retrieve().bodyToFlux(Airport.class);
    }

    public Mono<Airport> getAirport(final String iataCode) {
        URI uri = URI.create(airportProperties.getEndpoint());

        return Mono.from(
                webClient.method(HttpMethod.GET)
                        .uri(uri)
                        .retrieve()
                        .bodyToFlux(Airport.class)
                        .filter(airport -> airport.getIataCode().equals(iataCode)));

    }
}
