package com.example.demo.subject;

public class SubjectDto {
    private Long id;
    private String name;
    private Long teacherId;
    private int hours;
    private int credit;

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
}
