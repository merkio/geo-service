package com.company.chargingstations.service;

import com.company.chargingstations.controller.queryParams.SearchParameters;
import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.repository.ChargingStationRepository;
import com.company.chargingstations.util.BeanUtils;
import com.company.chargingstations.util.PointUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ChargingStationsServiceTest {

    @Mock
    private ChargingStationRepository stationRepository;

    @InjectMocks
    private ChargingStationsService stationsService;

    private ChargingStation station;

    @BeforeEach
    void setUp() {
        station = ChargingStation.builder()
            .id("B-324")
            .coordinates(PointUtils.create(54.1, 43.3))
            .postalCode("10000")
            .build();
        Mockito.lenient().when(stationRepository.save(Mockito.any())).thenReturn(station);
        Mockito.lenient().when(stationRepository.findById(station.getId())).thenReturn(Optional.of(station));

        Mockito.lenient().when(stationRepository.findAll(Mockito.any(Specification.class)))
            .thenReturn(Collections.singletonList(Optional.of(station)));

        Mockito.lenient().when(stationRepository.findAll(Mockito.any(Specification.class),
                                                         Mockito.any(Pageable.class)))
            .thenReturn(new PageImpl<>(Collections.singletonList(station)));
    }

    @Test
    void createStation() {
        ChargingStation savedStation = stationsService.createStation(BeanUtils.toDTO(station));

        assertEquals(station.getId(), savedStation.getId());
        assertEquals(station.getCoordinates().getX(), savedStation.getCoordinates().getX());
        assertEquals(station.getCoordinates().getY(), savedStation.getCoordinates().getY());
        assertEquals(station.getPostalCode(), savedStation.getPostalCode());
    }

    @Test
    void searchByPostalCode() {
        List<ChargingStation> stations = stationsService.search(SearchParameters.builder().postalCode(station.getPostalCode()).build());
        assertEquals(1, stations.size());
        assertEquals(station, stations.get(0));
    }

    @Test
    void searchByLocationAndRadius() {
        List<ChargingStation> stations = stationsService.search(SearchParameters.builder()
                                                                       .longitude(station.getCoordinates().getX())
                                                                       .latitude(station.getCoordinates().getY())
                                                                       .distance(50.0)
                                                                       .build());
        assertEquals(1, stations.size());
        assertEquals(station, stations.get(0));
    }
}