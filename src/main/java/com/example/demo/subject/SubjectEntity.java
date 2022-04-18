package com.example.demo.subject;

import com.example.demo.teacher.TeacherEntity;

import javax.persistence.*;


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
