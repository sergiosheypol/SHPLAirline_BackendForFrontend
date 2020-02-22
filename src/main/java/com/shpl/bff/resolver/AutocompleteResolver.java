package com.shpl.bff.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shpl.bff.model.Autocomplete;
import com.shpl.bff.model.City;
import com.shpl.bff.model.Coordinates;
import com.shpl.bff.model.Country;
import com.shpl.bff.service.CitiesService;
import com.shpl.bff.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutocompleteResolver implements GraphQLResolver<Autocomplete> {

    private final CountriesService countriesService;
    private final CitiesService citiesService;

    public City getCity(Autocomplete autocomplete) {
        return citiesService.getOne(autocomplete.getCity().get("code"));
    }

    public Country getCountry(Autocomplete autocomplete) {
        return countriesService.getOne(autocomplete.getCountry().get("code"));
    }

    public Coordinates getCoordinates(Autocomplete autocomplete) {
        return Coordinates.builder()
                .latitude(autocomplete.getCoordinates().get("latitude"))
                .longitude(autocomplete.getCoordinates().get("longitude"))
                .build();
    }

}
