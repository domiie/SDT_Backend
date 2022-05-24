package com.example.demo.subject.repository;

import com.example.demo.subject.entity.SubjectStudentPK;
import com.example.demo.subject.entity.SubjectStudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionsRepository extends CrudRepository<SubjectStudentEntity, SubjectStudentPK> {

  List<SubjectStudentEntity> findAll();

  Optional<SubjectStudentEntity> findBySubjectEntityIdAndUserEntityId(Long subjectId, Long studentId);

}
