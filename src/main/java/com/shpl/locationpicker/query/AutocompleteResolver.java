package com.shpl.locationpicker.query;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.shpl.locationpicker.model.Autocomplete;
import com.shpl.locationpicker.model.City;
import org.springframework.stereotype.Component;

@Component
public class AutocompleteResolver implements GraphQLResolver<Autocomplete> {

    public City getCity(Autocomplete autocomplete) {
        return City.builder()
                .code(autocomplete.getCity().get("code"))
                .name(autocomplete.getCity().get("name"))
                .build();
    }

}
