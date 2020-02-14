package com.shpl.locationpicker.service;

import com.shpl.locationpicker.model.Country;
import com.shpl.locationpicker.provider.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountriesService {

    private final DataProvider dataProvider;

    public List<Country> getCountries() {
        return dataProvider.getCountries().collectList().block();
    }

    public Country getCountry(final String code) {
        return dataProvider.getCountry(code).block();
    }
}
