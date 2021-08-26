package com.example.appwebhookbotserver.repository;

import com.example.appwebhookbotserver.entity.Role;
import com.example.appwebhookbotserver.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAllByRole(RoleName role);
}
