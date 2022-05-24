package com.example.demo.subject.entity;

import com.example.demo.authentication.dal.entity.UserEntity;
import com.example.demo.subject.enumeration.Assessment;
import com.example.demo.subject.enumeration.Status;
import com.example.demo.subject.enumeration.StudentStatus;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SubjectStudentEntity {

        @EmbeddedId
        private SubjectStudentPK id = new SubjectStudentPK();

        @ManyToOne
        @MapsId("subjectEntityId")
        private SubjectEntity subjectEntity;

        @ManyToOne
        @MapsId("userEntityId")
        private UserEntity userEntity;

        private LocalDateTime dateOfRegistration;
        private LocalDateTime lastModifiedDate;

        @Enumerated(EnumType.STRING)
        private StudentStatus status;

        @Enumerated(EnumType.STRING)
        private Assessment assessment;

        public Assessment getAssessment() {
                return assessment;
        }

        public void setAssessment(Assessment assessment) {
                this.assessment = assessment;
        }

        public LocalDateTime getDateOfRegistration() {
                return dateOfRegistration;
        }

        public void setDateOfRegistration(LocalDateTime dateOfRegistration) {
                this.dateOfRegistration = dateOfRegistration;
        }

        public SubjectStudentPK getId() {
                return id;
        }

        public void setId(SubjectStudentPK id) {
                this.id = id;
        }

        public SubjectEntity getSubjectEntity() {
                return subjectEntity;
        }

        public void setSubjectEntity(SubjectEntity subjectEntity) {
                this.subjectEntity = subjectEntity;
        }

        public UserEntity getUserEntity() {
                return userEntity;
        }

        public void setUserEntity(UserEntity userEntity) {
                this.userEntity = userEntity;
        }

        public StudentStatus getStatus() {
                return status;
        }

        public void setStatus(StudentStatus status) {
                this.status = status;
        }


        public LocalDateTime getLastModifiedDate() {
                return lastModifiedDate;
        }

        public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
                this.lastModifiedDate = lastModifiedDate;
        }


}
