package com.bugfearless.leave_management_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bugfearless.leave_management_system.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
}
