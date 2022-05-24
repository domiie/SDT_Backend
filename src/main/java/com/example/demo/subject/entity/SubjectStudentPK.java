package com.example.demo.subject.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SubjectStudentPK implements Serializable {

        private static final long serialVersionUID = 1L;

        private Long subjectEntityId;
        private Long userEntityId;

        public SubjectStudentPK() {

        }
        public SubjectStudentPK(Long subjectEntityId, Long userEntityId) {
            super();
            this.subjectEntityId = subjectEntityId;
            this.userEntityId = userEntityId;
        }

    public Long getSubjectEntityId() {
        return subjectEntityId;
    }

    public void setSubjectEntityId(Long subjectEntityId) {
        this.subjectEntityId = subjectEntityId;
    }

    public Long getUserEntityId() {
        return userEntityId;
    }

    public void setUserEntityId(Long userEntityId) {
        this.userEntityId = userEntityId;
    }

    @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result
                    + ((subjectEntityId == null) ? 0 : subjectEntityId.hashCode());
            result = prime * result
                    + ((userEntityId == null) ? 0 : userEntityId.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            SubjectStudentPK other = (SubjectStudentPK) obj;
            return Objects.equals(getSubjectEntityId(), other.getSubjectEntityId()) && Objects.equals(getUserEntityId(), other.getUserEntityId());
        }
}
