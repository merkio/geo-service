package com.company.chargingstations.util;

import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;

@Slf4j
public final class PointUtils {

    private static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();

    public static Point create(double longitude, double latitude) {
        return GEOMETRY_FACTORY.createPoint(new Coordinate(longitude, latitude));
    }
}
