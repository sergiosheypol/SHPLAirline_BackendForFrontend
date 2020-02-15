package com.shpl.locations.service;

import com.shpl.locations.model.Autocomplete;
import com.shpl.locations.provider.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AutocompleteService {
    private final DataProvider dataProvider;

    public List<Autocomplete> getAutocomplete(final String phrase) {
        return dataProvider.getAutocomplete(phrase).collectList().block();
    }
}
