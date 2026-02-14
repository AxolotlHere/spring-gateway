package com.axolotl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axolotl.entity.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Long>{


}
