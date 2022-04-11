package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public class StudentController {
    private StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    //CREATE USER
    @PostMapping("/api/students")
    public Long createStudent(@RequestBody StudentDto student){
        return studentService.createStudent(student);
    }

    //LIST USER
    @GetMapping("/api/student")
    public List<StudentDto> getStudents(@RequestParam(required = false) String lastname){
        return studentService.getStudents(lastname);
    }

    //GET USER BY ID
    @GetMapping("/api/users/{studentId}")
    public StudentDto getStudent(@PathVariable Long studentId){
        return studentService.getStudent(studentId);
    }

    //UPDATE USER
    @PutMapping("/api/users/{studentId}")
    public void updateStudent(@PathVariable Long studentId, @RequestBody Student student){
        studentService.updateStudent(studentId, student);
    }

    //DELETE USER
    @DeleteMapping("/api/users/{studentId}")
    public void deleteStudent(@PathVariable Long studentId){
        studentService.deleteStudent(studentId);
    }
}
