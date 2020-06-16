package com.company.chargingstations.repository;

import com.company.chargingstations.domain.ChargingStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ChargingStationRepository extends JpaRepository<ChargingStation, String>, JpaSpecificationExecutor<ChargingStation> {

}
