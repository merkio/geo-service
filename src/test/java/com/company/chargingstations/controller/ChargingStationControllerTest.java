package com.company.chargingstations.controller;

import com.company.chargingstations.domain.ChargingStation;
import com.company.chargingstations.dto.ChargingStationDTO;
import com.company.chargingstations.repository.ChargingStationRepository;
import com.company.chargingstations.testdata.TestData;
import com.company.chargingstations.util.BeanUtils;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ChargingStationControllerTest {

    @Value("http://localhost:${local.server.port}/v1/charging-stations")
    private String baseUrl;

    @Autowired
    private ChargingStationRepository stationRepository;

    private ChargingStationDTO chargingStationDTO;

    @BeforeEach
    void setUp() {
        stationRepository.deleteAll();

        chargingStationDTO = ChargingStationDTO.builder()
            .id("C-20")
            .latitude(50.0)
            .longitude(33.4)
            .postalCode("3200")
            .build();
    }

    @Test
    void createChargingStation() {

        //@formatter:off
        ChargingStation response =
            given()
                .contentType(ContentType.JSON)
                .baseUri(baseUrl)
                .body(chargingStationDTO)
            .when()
                .post()
                .prettyPeek()
            .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(ChargingStation.class);
        //@formatter:off
        assertEquals(BeanUtils.fromDTO(chargingStationDTO), response);
    }

    @Test
    void getById() {
        stationRepository.save(BeanUtils.fromDTO(chargingStationDTO));

        //@formatter:off
        ChargingStation response =
            given()
                .baseUri(baseUrl)
             .when()
                .get("/{id}", chargingStationDTO.getId())
                .prettyPeek()
            .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .body()
                .as(ChargingStation.class);
        //@formatter:off

        assertEquals(BeanUtils.fromDTO(chargingStationDTO), response);
    }

    @Test
    void searchByPostalCode() {
        List<ChargingStation> savedStations = stationRepository.saveAll(
            TestData.getStationsWithPostalCode("3333", 5));

        //@formatter:off
        List<ChargingStation> response =
            given()
                .baseUri(baseUrl)
                .queryParam("postalCode", "3333")
            .when()
                .get()
                .prettyPeek()
            .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList("", ChargingStation.class);
        //@formatter:off

        assertEquals(savedStations.size(), response.size());
    }

    @Test
    void searchByDistance() {
        List<ChargingStation> savedStations = stationRepository.saveAll(
            TestData.getStationsWithPostalCode("3333", 5));

        ChargingStation firstStation = savedStations.get(0);

        //@formatter:off
        List<ChargingStation> response =
            given()
                .baseUri(baseUrl)
                .queryParams("latitude", firstStation.getCoordinates().getY(),
                             "longitude", firstStation.getCoordinates().getX(), "distance", "1")
            .when()
                .get()
                .prettyPeek()
            .then()
                .statusCode(HttpStatus.OK.value())
                .extract()
                .jsonPath()
                .getList("", ChargingStation.class);
        //@formatter:off

        assertEquals(1, response.size());
    }

}
