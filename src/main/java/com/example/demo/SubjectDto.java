package com.example.demo;

public class SubjectDto {
    private String subjectName;
    private Long id;
    private int subjectHours;
    private int subjectCredits;

    public String getSubjectName() {
        return subjectName;
    }

    public Long getId() {
        return id;
    }

    public int getSubjectHours() {
        return subjectHours;
    }

    public int getSubjectCredits() {
        return subjectCredits;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubjectHours(int subjectHours) {
        this.subjectHours = subjectHours;
    }

    public void setSubjectCredits(int subjectCredits) {
        this.subjectCredits = subjectCredits;
    }
}
