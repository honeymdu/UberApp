package com.coding.project.uber.uberApp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.project.uber.uberApp.enities.Rider;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long>{

}
