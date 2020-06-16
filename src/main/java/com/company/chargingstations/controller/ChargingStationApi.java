package com.company.chargingstations.controller;

import com.company.chargingstations.controller.queryParams.SearchParameters;
import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.dto.ChargingStationDTO;

import java.util.List;

public interface ChargingStationApi {

    ChargingStation create(ChargingStationDTO chargingStation);

    ChargingStation get(String id);

    List<ChargingStation> search(SearchParameters searchParameters);
}
