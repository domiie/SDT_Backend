package com.example.demo.authentication.dal.repository;

import com.example.demo.authentication.dal.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @Override
    List<UserEntity> findAll();
    Optional<UserEntity> findById(Long userId);
    UserEntity findByUsername(String username);
    List<UserEntity> findByRoles(String role);
}


