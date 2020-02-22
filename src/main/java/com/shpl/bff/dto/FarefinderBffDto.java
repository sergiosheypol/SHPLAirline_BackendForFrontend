package com.shpl.bff.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FarefinderBffDto {
    private String departureDateFrom;
    private String departureDateTo;
    private String arrivalAirport;
    private String departureAirport;
    private String currencyCode;
}
