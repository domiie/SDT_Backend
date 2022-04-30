package com.example.demo.organization;

import com.example.demo.student.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepository extends CrudRepository<OrganizationEntity, Long> {
    @Override
    List<OrganizationEntity> findAll();
}
