package com.example.demo.subject.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

@Entity
public class Prerequisites {

    @EmbeddedId
    private PrerequisitePK id = new PrerequisitePK();

    @ManyToOne
    @MapsId("prerequisiteSubjectEntityId")
    private SubjectEntity prerequisiteSubjectEntity;


}
