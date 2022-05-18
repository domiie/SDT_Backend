package com.example.demo.subject.repository;

import com.example.demo.subject.entity.SubjectEntity;
import com.example.demo.subject.enumeration.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, Long>  {

    Optional<SubjectEntity> findBySubjectName(String subjectName);

    List<SubjectEntity> findByStatus(Status status);

}

