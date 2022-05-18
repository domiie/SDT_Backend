package com.example.demo.subject.controller;

import com.example.demo.subject.service.SubjectDto;
import com.example.demo.subject.service.SubjectListDto;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.subject.enumeration.Status;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/api/subjects")
    public List<SubjectListDto> getSubject(@RequestParam(required = false) Status status) {
        return subjectService.getSubjects(status);
    }

    @GetMapping("/api/subjects/{subjectId}")
    public SubjectListDto getSubjectsById(@PathVariable Long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    @PostMapping("/api/subjects")
    public Long createSubject(@RequestBody SubjectDto subjectDto){
        return subjectService.createSubject(subjectDto);
    }

    @DeleteMapping("/api/subjects/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
    }

    @PutMapping("/api/subjects/{subjectId}")
    public void updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDto subjectDto){
        subjectService.updateSubject(subjectId, subjectDto);
    }
}
