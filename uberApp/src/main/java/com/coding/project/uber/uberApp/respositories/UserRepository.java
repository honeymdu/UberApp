package com.coding.project.uber.uberApp.respositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coding.project.uber.uberApp.enities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
