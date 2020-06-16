package com.company.chargingstations.util;

import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.dto.ChargingStationDTO;

import javax.validation.constraints.NotNull;

public final class BeanUtils {

    @NotNull
    public static ChargingStation fromDTO(@NotNull ChargingStationDTO chargingStationDTO) {
        return ChargingStation.builder()
            .id(chargingStationDTO.getId())
            .coordinates(PointUtils.create(chargingStationDTO.getLongitude(), chargingStationDTO.getLatitude()))
            .postalCode(chargingStationDTO.getPostalCode())
            .build();
    }

    public static ChargingStationDTO toDTO(@NotNull ChargingStation chargingStation) {
        return ChargingStationDTO.builder()
            .id(chargingStation.getId())
            .longitude(chargingStation.getCoordinates().getX())
            .latitude(chargingStation.getCoordinates().getY())
            .postalCode(chargingStation.getPostalCode())
            .build();
    }
}
