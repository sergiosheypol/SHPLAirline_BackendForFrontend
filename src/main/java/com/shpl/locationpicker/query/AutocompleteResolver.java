package com.shpl.locationpicker.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shpl.locationpicker.model.Autocomplete;
import com.shpl.locationpicker.model.City;
import com.shpl.locationpicker.model.Coordinates;
import com.shpl.locationpicker.model.Country;
import com.shpl.locationpicker.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutocompleteResolver implements GraphQLResolver<Autocomplete> {

    private final CountriesService countriesService;

    public City getCity(Autocomplete autocomplete) {
        return City.builder()
                .code(autocomplete.getCity().get("code"))
                .name(autocomplete.getCity().get("name"))
                .build();
    }

    public Country getCountry(Autocomplete autocomplete) {
        return countriesService.getCountry(autocomplete.getCountry().get("code"));
    }

    public Coordinates getCoordinates(Autocomplete autocomplete) {
        return Coordinates.builder()
                .latitude(autocomplete.getCoordinates().get("latitude"))
                .longitude(autocomplete.getCoordinates().get("longitude"))
                .build();
    }

}
