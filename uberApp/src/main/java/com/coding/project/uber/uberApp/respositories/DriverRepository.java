package com.coding.project.uber.uberApp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.project.uber.uberApp.enities.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long>{

}
