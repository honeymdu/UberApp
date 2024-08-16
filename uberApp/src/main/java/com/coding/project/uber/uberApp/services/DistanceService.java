package com.coding.project.uber.uberApp.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {

     double CalculateDistance(Point src,Point dest);

}
