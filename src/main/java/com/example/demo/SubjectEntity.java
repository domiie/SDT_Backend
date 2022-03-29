package com.example.demo;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class SubjectEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String subjectName;
    private int subjectHours;
    private int subjectCredits;

    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getSubjectHours() {
        return subjectHours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setSubjectHours(int subjectHours) {
        this.subjectHours = subjectHours;
    }

    public void setSubjectCredits(int subjectCredits) {
        this.subjectCredits = subjectCredits;
    }

    public int getSubjectCredits() {
        return subjectCredits;
    }


}
