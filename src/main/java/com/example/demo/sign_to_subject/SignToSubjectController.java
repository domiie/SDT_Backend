package com.example.demo.sign_to_subject;

import org.springframework.web.bind.annotation.*;

import java.util.List;

public class SignToSubjectController {
    private SignToSubjectService signToSubjectService;

    public SignToSubjectController(SignToSubjectService signToSubjectService) {
        this.signToSubjectService = signToSubjectService;
    }

    @GetMapping("/api/signups")
    public List<SignToSubjectDto> getSubjects(@RequestParam(required = false) Long signToSubjectId) {
        return signToSubjectService.getSubjectSignUps(signToSubjectId);
    }

    @GetMapping("/api/signups/{signupId}")
    public SignToSubjectDto getAllSubjectSignUps(@PathVariable Long signToSubjectId) {
        return signToSubjectService.getSubjectSignUp(signToSubjectId);
    }

    @PostMapping("/api/signups")
    public Long createSubjectSignUp(@RequestBody SignToSubjectDto signToSubjectDto){
        return signToSubjectService.createSubjectSignUp(signToSubjectDto);
    }

    //DELETE CUSTOMER
    @DeleteMapping("/api/signups/{signupId}")
    public void deleteSubjectSignUp(@PathVariable Long signToSubjectId){
        signToSubjectService.deleteSubjectSignUp(signToSubjectId);
    }
}
