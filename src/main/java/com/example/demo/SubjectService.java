package com.example.demo;

import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository){
        this.subjectRepository = subjectRepository;
    }

    private static SubjectDto mapToSubjectDto(SubjectEntity subjectEntity) {
        SubjectDto subjectDto = new SubjectDto();

        subjectDto.setName(subjectEntity.getSubjectName());
        subjectDto.setHours(subjectEntity.getSubjectHours());
        subjectDto.setCredit(subjectEntity.getSubjectCredits());
        subjectDto.setId(subjectEntity.getId());

        return subjectDto;
    }

    @Transactional
    public List<SubjectDto> getSubjects(String subjectName) {
        List<SubjectDto> books = new LinkedList<>();
        for (SubjectEntity b1 : subjectRepository.findAll()) {
            SubjectDto b2 = mapToSubjectDto(b1);
            books.add(b2);
        }
        return books;
    }

    @Transactional
    public SubjectDto getSubjectByName(String subjectName){
        Optional<SubjectEntity> byTitle = subjectRepository.findBySubjectName(subjectName);

        if(byTitle.isPresent()){
            return mapToSubjectDto(byTitle.get());
        }

        return null;
    }

    @Transactional
    public SubjectDto getSubjectById(Long subjectId){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);

        if(byId.isPresent()){
            return mapToSubjectDto(byId.get());
        }

        return null;
    }

    @Transactional
    public Long createSubject(SubjectDto subject){
        SubjectEntity subjectEntity = new SubjectEntity();

        subjectEntity.setSubjectName(subject.getName());
        subjectEntity.setSubjectHours(subject.getHours());
        subjectEntity.setSubjectCredits(subject.getCredit());

        this.subjectRepository.save(subjectEntity);
        return subjectEntity.getId();
    }

    @Transactional
    public void deleteSubject(Long subjectId){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);

        if (byId.isPresent()) {
            subjectRepository.delete(byId.get());
        }
    }

    @Transactional
    public void updateSubject(Long subjectId, SubjectDto subjectDto){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);

        if (byId.isPresent()) {
            byId.get().setSubjectName(subjectDto.getName());
            byId.get().setSubjectHours(subjectDto.getHours());
            byId.get().setSubjectCredits(subjectDto.getCredit());
        }
    }
}

