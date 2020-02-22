package com.shpl.bff.service;


import com.shpl.bff.dto.FarefinderBffDto;
import com.shpl.bff.model.Fare;
import com.shpl.bff.provider.DataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class FareService {

    private final DataProvider dataProvider;

    public Flux<Fare> getFares(final FarefinderBffDto farefinderBffDto) {
        return dataProvider.getFares(farefinderBffDto);
    }
}
