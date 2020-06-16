package com.company.chargingstations.util;

import com.company.chargingstations.controller.queryParams.SearchParameters;
import com.company.chargingstations.domain.ChargingStation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.spatial.predicate.SpatialPredicates;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
public final class QueryUtils {

    public static Specification<ChargingStation> createSearchQuery(SearchParameters searchParameters) {
        return (Specification<ChargingStation>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNoneBlank(searchParameters.getPostalCode())) {
                predicates.add(criteriaBuilder.equal(root.get("postalCode"), searchParameters.getPostalCode()));
            }
            if (Objects.nonNull(searchParameters.getDistance())) {
                Point coordinates = PointUtils.create(searchParameters.getLongitude(), searchParameters.getLatitude());
                predicates.add(SpatialPredicates.distanceWithin(criteriaBuilder, root.get("coordinates"),
                                                                coordinates, searchParameters.getDistance()));
            }

            return predicates.stream().reduce(criteriaBuilder::and).orElse(null);
        };
    }
}

