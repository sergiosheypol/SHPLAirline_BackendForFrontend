package com.shpl.bff.provider;

import com.shpl.bff.dto.FarefinderBffDto;
import com.shpl.bff.model.Airport;
import com.shpl.bff.model.Autocomplete;
import com.shpl.bff.model.City;
import com.shpl.bff.model.Country;
import com.shpl.bff.model.Fare;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

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

    public Flux<Country> getCountries() {
        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder.path(dataProperties.getCountries()).build())
                .retrieve()
                .bodyToFlux(Country.class);
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

    public Flux<Fare> getFares(final FarefinderBffDto farefinderBffDto) {

        return webClient.method(HttpMethod.GET)
                .uri(uriBuilder -> uriBuilder
                        .path(dataProperties.getSingleFares())
                        .queryParam("departureDateFrom", farefinderBffDto.getDepartureDateFrom())
                        .queryParam("departureAirport", farefinderBffDto.getDepartureAirport())
                        .queryParam("arrivalAirport", farefinderBffDto.getArrivalAirport())
                        .queryParam("departureDateTo", farefinderBffDto.getDepartureDateTo())
                        .queryParam("currencyCode", farefinderBffDto.getCurrencyCode())
                        .build())
                .retrieve()
                .bodyToFlux(Fare.class);
    }
}
