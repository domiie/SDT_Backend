package com.example.demo.subject;

import com.example.demo.teacher.TeacherEntity;
import com.example.demo.teacher.TeacherRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private SubjectRepository subjectRepository;
    private TeacherRepository teacherRepository;

    public SubjectService(SubjectRepository subjectRepository, TeacherRepository teacherRepository){
        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
    }

    private static SubjectListDto mapToSubjectDto(SubjectEntity subjectEntity) {
        SubjectListDto subjectListDto = new SubjectListDto();

        subjectListDto.setName(subjectEntity.getSubjectName());
        subjectListDto.setTeacherId(subjectEntity.getTeacher().getId());
        subjectListDto.setTeacherFirstName(subjectEntity.getTeacher().getFirstName());
        subjectListDto.setTeacherLastName(subjectEntity.getTeacher().getLastName());
        subjectListDto.setHours(subjectEntity.getSubjectHours());
        subjectListDto.setCredit(subjectEntity.getSubjectCredits());
        subjectListDto.setId(subjectEntity.getId());

        return subjectListDto;
    }

    @Transactional
    public List<SubjectListDto> getSubjects(String subjectName) {
        List<SubjectListDto> books = new LinkedList<>();
        for (SubjectEntity b1 : subjectRepository.findAll()) {
            SubjectListDto b2 = mapToSubjectDto(b1);
            books.add(b2);
        }
        return books;
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

        Optional<TeacherEntity> teacher = teacherRepository.findById(subject.getTeacherId());

        subjectEntity.setSubjectName(subject.getName());
        subjectEntity.setSubjectHours(subject.getHours());
        subjectEntity.setSubjectCredits(subject.getCredit());
        subjectEntity.setTeacher(teacher.get());

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
            byId.get().setTeacher(teacherRepository.findById(subjectDto.getTeacherId()).get());
            byId.get().setSubjectHours(subjectDto.getHours());
            byId.get().setSubjectCredits(subjectDto.getCredit());
        }
    }
}
