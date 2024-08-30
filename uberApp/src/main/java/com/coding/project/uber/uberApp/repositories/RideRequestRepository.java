package com.coding.project.uber.uberApp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.project.uber.uberApp.enities.RideRequest;

@Repository
public interface RideRequestRepository extends JpaRepository<RideRequest,Long> {

}
