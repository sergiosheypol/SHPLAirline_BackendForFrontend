package com.shpl.locationpicker.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Autocomplete {
    private String code;
    private String name;
    private Map<String, String> city;
}
