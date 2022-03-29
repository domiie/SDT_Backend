package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    private SubjectService subjectService;

    public SubjectController(SubjectService subjectService){
        this.subjectService = subjectService;
    }

    @GetMapping("/api/subjects")
    public List<SubjectDto> getSubject(@RequestParam(required = false) String subjectName) {
        return subjectService.getSubjects(subjectName);
    }

    //Hľadanie knihy podľa ID
    @GetMapping("/api/subjects/{subjectId}")
    public SubjectDto getSubjectsById(@PathVariable Long subjectId){
        return subjectService.getSubjectById(subjectId);
    }

    //Pridanie novej knihy
    @PostMapping("/api/subjects")
    public Long createSubject(@RequestBody SubjectDto subjectDto){
       return subjectService.createSubject(subjectDto);
    }

    //Zmazanie knihy podľa ID
    @DeleteMapping("/api/subjects/{subjectId}")
    public void deleteSubject(@PathVariable Long subjectId){
        subjectService.deleteSubject(subjectId);
    }

    //Aktualizácia knihy podľa ID
    @PutMapping("/api/subjects/{subjectId}")
    public void updateSubject(@PathVariable Long subjectId, @RequestBody SubjectDto subjectDto){
        subjectService.updateSubject(subjectId, subjectDto);
    }
}

