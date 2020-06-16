package com.company.chargingstations.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargingStationDTO {

    private String id;
    private String postalCode;
    private double longitude;
    private double latitude;
}
