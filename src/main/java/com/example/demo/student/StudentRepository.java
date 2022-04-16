package com.example.demo.student;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Long> {
    @Override
    List<StudentEntity> findAll();
    Optional<StudentEntity> findById(Integer studentId);
}
