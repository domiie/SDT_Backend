package com.example.demo.teacher;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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


    @GetMapping("/api/teachers/{teacherId}")
    public TeacherDto getTeacher(@PathVariable Long teacherId){
        return teacherService.getTeacher(teacherId);
    }


    @PutMapping("/api/teachers/{teacherId}")
    public void updateTeacher(@PathVariable Long teacherId, @RequestBody Teacher teacher){
        teacherService.updateTeacher(teacherId, teacher);
    }


    @DeleteMapping("/api/teachers/{teacherId}")
    public void deleteTeacher(@PathVariable Long teacherId){
        teacherService.deleteTeacher(teacherId);
    }
}
