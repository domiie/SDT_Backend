package com.example.demo.subject.service;

import com.example.demo.authentication.dal.entity.UserEntity;
import com.example.demo.authentication.dal.repository.UserRepository;
import com.example.demo.authentication.dal.service.UserService;
import com.example.demo.subject.repository.SubjectRepository;
import com.example.demo.subject.entity.SubjectEntity;
import com.example.demo.subject.enumeration.Status;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository){
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    private SubjectListDto mapToSubjectDto(SubjectEntity subjectEntity) {
        SubjectListDto subjectListDto = new SubjectListDto();

        subjectListDto.setName(subjectEntity.getSubjectName());
        subjectListDto.setTeacherId(subjectEntity.getTeacher().getId());
        subjectListDto.setTeacherFirstName(subjectEntity.getTeacher().getFirstName());
        subjectListDto.setTeacherLastName(subjectEntity.getTeacher().getLastName());
        subjectListDto.setHours(subjectEntity.getSubjectHours());
        subjectListDto.setCredit(subjectEntity.getSubjectCredits());
        subjectListDto.setStatus(subjectEntity.getStatus());
        subjectListDto.setCreationDate(subjectEntity.getCreationDate());
        subjectListDto.setLastChangeDate(subjectEntity.getLastChangeDate());
        subjectListDto.setLocked(subjectEntity.isLocked());
        subjectListDto.setId(subjectEntity.getId());

        return subjectListDto;
    }

    @Transactional
    public List<SubjectListDto> getSubjects(Status status) {
        List<SubjectListDto> subjects = new LinkedList<>();

        if(status!=null){
            for (SubjectEntity b1 : subjectRepository.findByStatus(status)) {
                SubjectListDto b2 = mapToSubjectDto(b1);
                subjects.add(b2);
            }
        }else {
            for (SubjectEntity b1 : subjectRepository.findAll()) {
                SubjectListDto b2 = mapToSubjectDto(b1);
                subjects.add(b2);
            }
        }

        return subjects;
    }


    @Transactional
    public SubjectListDto getSubjectByName(String subjectName){
        Optional<SubjectEntity> byTitle = subjectRepository.findBySubjectName(subjectName);

        if(byTitle.isPresent()){
            return mapToSubjectDto(byTitle.get());
        }

        return null;
    }

    @Transactional
    public List<SubjectListDto> getSubjectsByHours(int subjectHours){
        List<SubjectListDto> hours = new LinkedList<>();

        if(hours != null){
            for (SubjectEntity b1 : subjectRepository.findBySubjectHours(subjectHours)) {
                SubjectListDto b2 = mapToSubjectDto(b1);
                hours.add(b2);
            }
        }else {
            for (SubjectEntity b1 : subjectRepository.findAll()) {
                SubjectListDto b2 = mapToSubjectDto(b1);
                hours.add(b2);
            }
        }

        return hours;
    }

    @Transactional
    public List<SubjectListDto> getSubjectsByCredits(int subjectCredits) {
        List<SubjectListDto> credits = new LinkedList<>();

        if(credits != null){
            for (SubjectEntity b1 : subjectRepository.findBySubjectCredits(subjectCredits)) {
                SubjectListDto b2 = mapToSubjectDto(b1);
                credits.add(b2);
            }
        }else {
            for (SubjectEntity b1 : subjectRepository.findAll()) {
                SubjectListDto b2 = mapToSubjectDto(b1);
                credits.add(b2);
            }
        }

        return credits;
    }


    @Transactional
    public SubjectListDto getSubjectById(Long subjectId){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);

        if(byId.isPresent()){
            return mapToSubjectDto(byId.get());
        }

        return null;
    }

    @Transactional
    public Long createSubject(SubjectDto subject){
        SubjectEntity subjectEntity = new SubjectEntity();

        Optional<UserEntity> user = userRepository.findById(subject.getTeacherId());

        subjectEntity.setSubjectName(subject.getName());
        subjectEntity.setSubjectHours(subject.getHours());
        subjectEntity.setSubjectCredits(subject.getCredit());
        subjectEntity.setStatus(subject.getStatus());
        subjectEntity.setTeacher(user.get());
        subjectEntity.setCreationDate(LocalDate.now());
        subjectEntity.setLocked(false);
        subjectEntity.setLastChangeDate(LocalDateTime.now());

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
//            byId.get().setTeacher(userRepository.findById(subjectDto.getTeacherId()).get());
            byId.get().setSubjectHours(subjectDto.getHours());
            byId.get().setStatus(subjectDto.getStatus());
            byId.get().setSubjectCredits(subjectDto.getCredit());
            byId.get().setCreationDate(subjectDto.getCreationDate());
            byId.get().setLocked(subjectDto.isLocked());
            byId.get().setLastChangeDate(LocalDateTime.now());
        }
    }


}
