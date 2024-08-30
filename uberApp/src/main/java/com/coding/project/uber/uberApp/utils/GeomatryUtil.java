package com.coding.project.uber.uberApp.utils;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

import com.coding.project.uber.uberApp.dto.PointDto;

public class GeomatryUtil {

    public static Point CreatePoint(PointDto pointDto) {
        
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate coordinate = new Coordinate(pointDto.getCoordianates()[0], pointDto.getCoordianates()[1]);
        return geometryFactory.createPoint(coordinate);

    }

}
