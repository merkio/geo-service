package com.company.chargingstations.service;

import com.company.chargingstations.controller.queryParams.SearchParameters;
import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.dto.ChargingStationDTO;

import java.util.List;

public interface IChargingStationsService {

    ChargingStation createStation(ChargingStationDTO chargingStation);

    ChargingStation getStation(String id);

    List<ChargingStation> search(SearchParameters searchParameters);
}
