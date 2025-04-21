package com.devteria.indentity_service.repository;

import com.devteria.indentity_service.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepositopry extends JpaRepository<Permission, String> {

}
