package com.shpl.locations.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shpl.locations.model.Airport;
import com.shpl.locations.model.Autocomplete;
import com.shpl.locations.model.City;
import com.shpl.locations.model.Country;
import com.shpl.locations.service.AirportsService;
import com.shpl.locations.service.AutocompleteService;
import com.shpl.locations.service.CitiesService;
import com.shpl.locations.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueriesResolver implements GraphQLQueryResolver {

    private final AirportsService airportsService;
    private final CountriesService countriesService;
    private final AutocompleteService autocompleteService;
    private final CitiesService citiesService;

    public List<Airport> getAirports() {
        return airportsService.getAll();
    }

    public Airport getAirport(final String iataCode) {
        return airportsService.getOne(iataCode);
    }

    public List<Country> getCountries() {
        return countriesService.getAll();
    }

    public Country getCountry(final String code) {
        return countriesService.getOne(code);
    }

    public List<City> getCities() {
        return citiesService.getAll();
    }

    public City getCity(final String code) {
        return citiesService.getOne(code);
    }

    public List<Autocomplete> getAutocomplete(final String phrase) {
        return autocompleteService.getAutocomplete(phrase);
    }


}
