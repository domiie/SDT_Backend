package com.example.demo.subject.controller;

import com.example.demo.authentication.dal.entity.UserEntity;
import com.example.demo.subject.service.SubjectDto;
import com.example.demo.subject.service.SubjectListDto;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.subject.enumeration.Status;
import org.springframework.security.core.userdetails.User;
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

    @GetMapping("/api/subjects/hours")
    public List<SubjectListDto> getSubjectsByHours(@RequestParam(required = false) int hours) {
        return subjectService.getSubjectsByHours(hours);
    }

    @GetMapping("/api/subjects/credits")
    public List<SubjectListDto> getSubjectsByCredits(@RequestParam(required = false) int credits) {
        return subjectService.getSubjectsByCredits(credits);
    }

    @GetMapping("/api/subjects/lastname")
    public List<SubjectListDto> getSubjectsByTeacherFirstName(@RequestParam(required = false) String lastname) {
        return subjectService.getSubjectsByTeacherLastName(lastname);
    }

    @GetMapping("/api/subjects/firstname")
    public List<SubjectListDto> getSubjectsByTeacherLastName(@RequestParam(required = false) String firstname) {
        return subjectService.getSubjectsByTeacherFirstName(firstname);
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
