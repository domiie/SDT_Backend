package com.example.demo.subject;

import com.example.demo.teacher.TeacherEntity;

import javax.annotation.Generated;
import javax.persistence.*;


@Entity
public class SubjectEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String subjectName;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private TeacherEntity teacher;

    private int subjectHours;
    private int subjectCredits;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
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
