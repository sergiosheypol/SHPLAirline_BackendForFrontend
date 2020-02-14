package com.shpl.locationpicker.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.shpl.locationpicker.model.Airport;
import com.shpl.locationpicker.model.Autocomplete;
import com.shpl.locationpicker.model.Country;
import com.shpl.locationpicker.service.AirportsService;
import com.shpl.locationpicker.service.AutocompleteService;
import com.shpl.locationpicker.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class QueriesResolver implements GraphQLQueryResolver {

    private final AirportsService airportsService;
    private final CountriesService countriesService;
    private final AutocompleteService autocompleteService;

    public List<Airport> getAirports() {
        return airportsService.getAirports();
    }

    public Airport getAirport(final String iataCode) {
        return airportsService.getAirport(iataCode);
    }

    public List<Country> getCountries() {
        return countriesService.getCountries();
    }

    public Country getCountry(final String code) {
        return countriesService.getCountry(code);
    }

    public List<Autocomplete> getAutocomplete(final String phrase) {
        return autocompleteService.getAutocomplete(phrase);
    }

}
