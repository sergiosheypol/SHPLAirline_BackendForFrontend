package com.shpl.locationpicker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String[] routes;
}
