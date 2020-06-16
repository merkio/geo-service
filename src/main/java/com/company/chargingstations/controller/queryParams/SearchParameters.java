package com.company.chargingstations.controller.queryParams;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchParameters {

    private String postalCode;
    private double longitude;
    private double latitude;
    private Double distance;
    @Builder.Default
    private int pageSize = 25;
    @Builder.Default
    private int pageNumber = 0;
}
