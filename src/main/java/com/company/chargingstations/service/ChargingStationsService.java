package com.company.chargingstations.service;

import com.company.chargingstations.controller.queryParams.SearchParameters;
import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.dto.ChargingStationDTO;
import com.company.chargingstations.repository.ChargingStationRepository;
import com.company.chargingstations.util.BeanUtils;
import com.company.chargingstations.util.QueryUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static com.company.chargingstations.exception.ResourceNotFoundException.newResourceNotFoundException;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChargingStationsService implements IChargingStationsService {

    private final ChargingStationRepository chargingStationRepository;

    @Override
    public ChargingStation createStation(@NotNull ChargingStationDTO chargingStationDTO) {
        log.info("Create new charging station with id [{}]", chargingStationDTO.getId());
        return chargingStationRepository.save(BeanUtils.fromDTO(chargingStationDTO));
    }

    @Override
    public ChargingStation getStation(@NotNull String id) {
        log.info("Get charging station by id [{}]", id);
        return chargingStationRepository.findById(id).orElseThrow(newResourceNotFoundException("Charging Station", "id", id));
    }

    @Override
    public List<ChargingStation> search(@NotNull SearchParameters searchParameters) {
        log.info("Searching charging stations with params [{}]", searchParameters);
        List<ChargingStation> searchResult = chargingStationRepository.findAll(QueryUtils.createSearchQuery(searchParameters),
                                                                               PageRequest.of(searchParameters.getPageNumber(),
                                                                                              searchParameters.getPageSize())).getContent();

        return new ArrayList<>(searchResult);
    }
}
