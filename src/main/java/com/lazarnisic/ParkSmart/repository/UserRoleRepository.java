package com.lazarnisic.ParkSmart.repository;

import com.lazarnisic.ParkSmart.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
}
