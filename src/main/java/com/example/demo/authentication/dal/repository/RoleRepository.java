package com.example.demo.authentication.dal.repository;

import com.example.demo.authentication.dal.entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {
    Optional<RoleEntity> findById(Long id);
    Optional<RoleEntity> findByRoleName(String name);
}
