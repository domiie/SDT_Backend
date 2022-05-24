package com.example.demo.subject.entity;

import com.example.demo.authentication.dal.entity.UserEntity;
import com.example.demo.subject.enumeration.Language;
import com.example.demo.subject.enumeration.Semester;
import com.example.demo.subject.enumeration.Status;
import com.example.demo.subject.enumeration.SubjectType;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class SubjectEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String subjectName;
    private String abbreviation;

    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity teacher;

    @OneToMany(mappedBy = "subjectEntity")
    private Set<SubjectStudentEntity> subscribedUsers = new HashSet<>();

    @OneToMany(mappedBy = "prerequisiteSubjectEntity")
    private Set<Prerequisites> prerequisites = new HashSet<>();

    private int subjectHours;
    private int subjectCredits;
    private String subjectCode;

    @Enumerated(EnumType.STRING)
    private Semester semester;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private SubjectType subjectType;

    @Enumerated(EnumType.STRING)
    private Language language;

    private LocalDate creationDate;
    private LocalDateTime lastChangeDate;
    private boolean accessible;

    public boolean isAccessible() {
        return accessible;
    }

    public void setAccessible(boolean accessible) {
        this.accessible = accessible;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastChangeDate() {
        return lastChangeDate;
    }

    public void setLastChangeDate(LocalDateTime lastChangeDate) {
        this.lastChangeDate = lastChangeDate;
    }

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

    public UserEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(UserEntity teacher) {
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


    public void setSubscribedUsers(Set<SubjectStudentEntity> subscribedUsers) {
        this.subscribedUsers = subscribedUsers;
    }

    public Set<SubjectStudentEntity> getSubscribedUsers() {
        return subscribedUsers;
    }
    public Set<Prerequisites> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(Set<Prerequisites> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public SubjectType getSubjectType() {
        return subjectType;
    }

    public void setSubjectType(SubjectType subjectType) {
        this.subjectType = subjectType;
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
