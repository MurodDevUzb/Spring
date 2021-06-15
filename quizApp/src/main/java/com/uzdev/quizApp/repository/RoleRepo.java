package com.uzdev.quizApp.repository;

import com.uzdev.quizApp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Long> {
}
