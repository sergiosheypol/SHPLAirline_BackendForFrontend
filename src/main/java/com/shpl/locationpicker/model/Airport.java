package com.shpl.locationpicker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Airport {
    private String iataCode;
    private String name;
    private String seoName;
    private String countryCode;
    private String regionCode;
    private String cityCode;
    private String currencyCode;
    private List<String> routes;
}
