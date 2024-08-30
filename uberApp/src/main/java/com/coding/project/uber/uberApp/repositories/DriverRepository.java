package com.coding.project.uber.uberApp.repositories;

import java.util.List;

import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.coding.project.uber.uberApp.enities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

    @Query(value = "SELECT d.*, ST_Distance(d.current_location , :pickupLocation) AS distance "
            + "FROM Driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location , :pickupLocation, 10000) "
            + "ORDER BY distance "
            + "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearestDriver(Point pickupLocation);

    @Query(value = "SELECT d.* "
            + "FROM Driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location , :pickupLocation, 15000) "
            + "ORDER BY  d.rating DESC "
            + "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearByTopRatedDriver(Point pickupLocation);

}