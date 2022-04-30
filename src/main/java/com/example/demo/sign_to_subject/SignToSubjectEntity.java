package com.example.demo.sign_to_subject;

import com.example.demo.authentication.dal.UserEntity;
import com.example.demo.subject.SubjectEntity;

import javax.persistence.*;

@Entity
public class SignToSubjectEntity {
    @Id
    @GeneratedValue
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private UserEntity user;

    @JoinColumn(name = "subject_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private SubjectEntity subject;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public SubjectEntity getSubject() {
        return subject;
    }

    public void setSubject(SubjectEntity subject) {
        this.subject = subject;
    }
}
