package com.shpl.bff.service;

import com.shpl.bff.model.Autocomplete;
import com.shpl.bff.provider.DataProvider;
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
