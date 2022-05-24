package com.example.demo.subject.entity;


import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class PrerequisitePK implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long prerequisiteSubjectEntityId;
    private Long mainSubjectEntityId;

    public Long getPrerequisiteSubjectEntityId() {
        return prerequisiteSubjectEntityId;
    }

    public void setPrerequisiteSubjectEntityId(Long firstSubjectEntityId) {
        this.prerequisiteSubjectEntityId = firstSubjectEntityId;
    }

    public Long getMainSubjectEntityId() {
        return mainSubjectEntityId;
    }

    public void setMainSubjectEntityId(Long mainSubjectEntityId) {
        this.mainSubjectEntityId = mainSubjectEntityId;
    }

    public PrerequisitePK() {

    }
    public PrerequisitePK(Long prerequisiteSubjectEntityId, Long mainSubjectEntityId) {
        super();
        this.prerequisiteSubjectEntityId = prerequisiteSubjectEntityId;
        this.mainSubjectEntityId = mainSubjectEntityId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((prerequisiteSubjectEntityId == null) ? 0 : prerequisiteSubjectEntityId.hashCode());
        result = prime * result
                + ((mainSubjectEntityId == null) ? 0 : mainSubjectEntityId.hashCode());
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
        return Objects.equals(getPrerequisiteSubjectEntityId(), other.getSubjectEntityId()) && Objects.equals(getMainSubjectEntityId(), other.getUserEntityId());
    }
}
