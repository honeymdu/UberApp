package com.coding.project.uber.uberApp.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coding.project.uber.uberApp.enities.Rider;
import com.coding.project.uber.uberApp.enities.User;

@Repository
public interface RiderRepository extends JpaRepository<Rider, Long> {

    Optional<Rider> findByUser(User user);

}
