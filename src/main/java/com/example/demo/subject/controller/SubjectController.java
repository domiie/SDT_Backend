package com.example.demo.subject.controller;

import com.example.demo.subject.service.SubjectDto;
import com.example.demo.subject.service.SubjectListDto;
import com.example.demo.subject.service.SubjectService;
import com.example.demo.subject.enumeration.Status;
import com.example.demo.subject.service.SubscribeDto;
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

    @PutMapping("/api/subjects/lock")
    public void lockSubjects(){
        subjectService.lockSubjects();
    }

    @PutMapping("/api/subjects/unlock")
    public void unlockSubjects(){
        subjectService.unlockSubjects();
    }

    @PutMapping("/api/subjects/lock/{subjectId}")
    public void lockSubjectById(@PathVariable Long subjectId){
        subjectService.lockSubjectById(subjectId);
    }

    @PostMapping("/api/subjects/subscribe")
    public void lockSubjectById(@RequestBody SubscribeDto subscribeDto){
          subjectService.subscribeForSubject(subscribeDto);
    }

    @GetMapping("/api/subjects/subscribe/{subjectId}/{studentId}")
    public SubscribeDto getSubscription(@PathVariable Long subjectId, @PathVariable Long studentId){
        return subjectService.getSubscription(subjectId, studentId);
    }

    @DeleteMapping("/api/subjects/unsubscribe")
    public void unsubscribeSubject(@RequestBody SubscribeDto subscribeDto){
        subjectService.unsubscribeFromSubject(subscribeDto);
    }

}
