package com.example.demo.subject;

import org.springframework.data.domain.Example;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends CrudRepository<SubjectEntity, Long>  {

    Optional<SubjectEntity> findBySubjectName(String subjectName);

    List<SubjectEntity> findByStatus(Status status);

}

