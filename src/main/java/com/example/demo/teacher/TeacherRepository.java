package com.example.demo.teacher;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends CrudRepository<TeacherEntity, Long> {
    @Override
    List<TeacherEntity> findAll();
    Optional<TeacherEntity> findById(Integer teacherId);
}
