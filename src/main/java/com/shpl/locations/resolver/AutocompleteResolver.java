package com.shpl.locations.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shpl.locations.model.Autocomplete;
import com.shpl.locations.model.City;
import com.shpl.locations.model.Coordinates;
import com.shpl.locations.model.Country;
import com.shpl.locations.service.CitiesService;
import com.shpl.locations.service.CountriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AutocompleteResolver implements GraphQLResolver<Autocomplete> {

    private final CountriesService countriesService;
    private final CitiesService citiesService;

    public City getCity(Autocomplete autocomplete) {
        return citiesService.getCity(autocomplete.getCity().get("code"));
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
