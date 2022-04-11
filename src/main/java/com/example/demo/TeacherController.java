package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public class TeacherController {
    private TeacherService teacherService;

    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }


    @PostMapping("/api/teachers")
    public Long createTeacher(@RequestBody TeacherDto teacher){
        return teacherService.createTeacher(teacher);
    }


    @GetMapping("/api/teachers")
    public List<TeacherDto> getTeachers(@RequestParam(required = false) String lastname){
        return teacherService.getTeachers(lastname);
    }


    @GetMapping("/api/users/{teacherId}")
    public TeacherDto getTeacher(@PathVariable Long teacherId){
        return teacherService.getTeacher(teacherId);
    }


    @PutMapping("/api/users/{teacherId}")
    public void updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher){
        teacherService.updateTeacher(teacherId, teacher);
    }


    @DeleteMapping("/api/users/{teacherId}")
    public void deleteTeacher(@PathVariable Long teacherId){
        teacherService.deleteTeacher(teacherId);
    }
}
