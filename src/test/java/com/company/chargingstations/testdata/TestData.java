package com.company.chargingstations.testdata;

import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.util.PointUtils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestData {

    public static List<ChargingStation> getStationsWithPostalCode(String postalCode, int amount) {
        return IntStream.range(0, amount)
            .mapToObj(i -> ChargingStation.builder()
                .id("Station_" + i)
                .coordinates(PointUtils.create(i * 22, i * 33))
                .postalCode(postalCode)
                .build())
            .collect(Collectors.toList());
    }
}
