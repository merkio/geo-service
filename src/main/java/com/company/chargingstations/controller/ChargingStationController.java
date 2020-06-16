package com.company.chargingstations.controller;

import com.company.chargingstations.controller.queryParams.SearchParameters;
import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.dto.ChargingStationDTO;
import com.company.chargingstations.service.IChargingStationsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/charging-stations")
@RequiredArgsConstructor
public class ChargingStationController implements ChargingStationApi {

    private final IChargingStationsService chargingStationsService;

    @Override
    @PostMapping(
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ChargingStation create(@Valid @RequestBody ChargingStationDTO chargingStation) {
        return chargingStationsService.createStation(chargingStation);
    }

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ChargingStation> search(SearchParameters searchParameters) {
        return chargingStationsService.search(searchParameters);
    }

    @Override
    @GetMapping(
        value = "/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ChargingStation get(@PathVariable("id") String id) {
        return chargingStationsService.getStation(id);
    }
}
