package com.example.demo.subject.service;

import com.example.demo.subject.enumeration.Language;
import com.example.demo.subject.enumeration.Semester;
import com.example.demo.subject.enumeration.Status;
import com.example.demo.subject.enumeration.SubjectType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class SubjectDto {
    private Long id;
    private String name;
    private Long teacherId;
    private int hours;
    private int credit;
    private SubjectType subjectType;
    private String abbreviation;
    private Status status;
    private String subjectCode;
    private Semester semester;
    private Language language;
    private boolean accessible;
    private String teacherFirstName;
    private String teacherLastName;

    private List<Long> subscribedStudents;

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    @JsonFormat(locale = "en", pattern = "YYYY-MM-dd")
    private LocalDate creationDate;

    @JsonFormat(locale = "en", pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastChangeDate;

    public LocalDateTime getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(LocalDateTime lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Long getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }

    public int getHours() {
        return hours;
    }

    public int getCredit() {
        return credit;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public List<Long> getSubscribedStudents() {
        return subscribedStudents;
    }

    public void setSubscribedStudents(List<Long> subscribedStudents) {
        this.subscribedStudents = subscribedStudents;
    }


    public String getTeacherFirstName() {
        return teacherFirstName;
    }

    public void setTeacherFirstName(String teacherFirstName) {
        this.teacherFirstName = teacherFirstName;
    }

    public String getTeacherLastName() {
        return teacherLastName;
    }

    public void setTeacherLastName(String teacherLastName) {
        this.teacherLastName = teacherLastName;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


}
