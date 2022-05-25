package com.example.demo.subject.service;

import com.example.demo.authentication.dal.entity.RoleEntity;
import com.example.demo.authentication.dal.entity.UserEntity;
import com.example.demo.authentication.dal.repository.RoleRepository;
import com.example.demo.authentication.dal.repository.UserRepository;
import com.example.demo.subject.entity.SubjectStudentEntity;
import com.example.demo.subject.enumeration.Assessment;
import com.example.demo.subject.enumeration.StudentStatus;
import com.example.demo.subject.repository.SubjectRepository;
import com.example.demo.subject.entity.SubjectEntity;
import com.example.demo.subject.enumeration.Status;
import com.example.demo.subject.repository.SubscriptionsRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final SubscriptionsRepository subscriptionsRepository;

    public SubjectService(SubjectRepository subjectRepository, UserRepository userRepository,
                          RoleRepository roleRepository, SubscriptionsRepository subscriptionsRepository){
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.subscriptionsRepository = subscriptionsRepository;
    }

    private static List<Long> retrieveStudents(SubjectEntity course){
        List<Long> students = new LinkedList<>();
        for(SubjectStudentEntity user : course.getSubscribedUsers()){
            students.add(user.getUserEntity().getId());
        }
        return students;
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
        subjectListDto.setAccessible(subjectEntity.isAccessible());
        subjectListDto.setSubscribedStudents(retrieveStudents(subjectEntity));
        subjectListDto.setSubjectType(subjectEntity.getSubjectType());
        subjectListDto.setAbbreviation(subjectEntity.getAbbreviation());
        subjectListDto.setSubjectCode(subjectEntity.getSubjectCode());
        subjectListDto.setSemester(subjectEntity.getSemester());
        subjectListDto.setLanguage(subjectEntity.getLanguage());
        subjectListDto.setId(subjectEntity.getId());

        return subjectListDto;
    }

    @Transactional
    public List<SubjectListDto> getSubjects(Status status) {
        List<SubjectListDto> subjects = new LinkedList<>();

        if(status!=null){
            for (SubjectEntity course : subjectRepository.findByStatus(status)) {
                SubjectListDto foundCourse = mapToSubjectDto(course);
                subjects.add(foundCourse);
            }
        }else {
            for (SubjectEntity course : subjectRepository.findAll()) {
                SubjectListDto foundCourse = mapToSubjectDto(course);
                subjects.add(foundCourse);
            }
        }

        return subjects;
    }


    @Transactional
    public SubjectListDto getSubjectByName(String subjectName){
        Optional<SubjectEntity> byTitle = subjectRepository.findBySubjectName(subjectName);
        return byTitle.map(this::mapToSubjectDto).orElse(null);
    }

    @Transactional
    public SubjectListDto getSubjectById(Long subjectId){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);
        return byId.map(this::mapToSubjectDto).orElse(null);

    }

    @Transactional
    public Long createSubject(SubjectDto subject){
        SubjectEntity subjectEntity = new SubjectEntity();
        Optional<UserEntity> user = userRepository.findById(subject.getTeacherId());
        subjectEntity.setSubjectName(subject.getName());
        subjectEntity.setAbbreviation(subject.getAbbreviation());
        subjectEntity.setSubjectHours(subject.getHours());
        subjectEntity.setSubjectCredits(subject.getCredit());
        subjectEntity.setStatus(subject.getStatus());
        subjectEntity.setTeacher(user.get());
        subjectEntity.setCreationDate(LocalDate.now());
        subjectEntity.setAccessible(subject.isAccessible());
        subjectEntity.setLastChangeDate(LocalDateTime.now());
        subjectEntity.setSubjectType(subject.getSubjectType());
        subjectEntity.setSubjectCode(subject.getSubjectCode());
        subjectEntity.setSemester(subject.getSemester());
        subjectEntity.setLanguage(subject.getLanguage());
        this.subjectRepository.save(subjectEntity);
        return subjectEntity.getId();
    }

    @Transactional
    public void deleteSubject(Long subjectId){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);
        byId.ifPresent(subjectRepository::delete);
    }


    @Transactional
    public void updateSubject(Long subjectId, SubjectDto subjectDto){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);
        if (byId.isPresent()) {
            if(subjectDto.getStatus().equals(Status.CANCELLED)) {
                SubscribeDto subscribeDto = new SubscribeDto();
                subscribeDto.setSubjectId(subjectId);
                for(SubjectStudentEntity subscription: byId.get().getSubscribedUsers()){
                    subscribeDto.setStudentId(subscription.getUserEntity().getId());
                    unsubscribeFromSubject(subscribeDto);
                }
            }
            byId.get().setStatus(subjectDto.getStatus());
            byId.get().setSubjectName(subjectDto.getName());
            byId.get().setTeacher(userRepository.findById(subjectDto.getTeacherId()).get());
            byId.get().setSubjectHours(subjectDto.getHours());
            byId.get().setSubjectCredits(subjectDto.getCredit());
            byId.get().setAccessible(subjectDto.isAccessible());
            byId.get().setLastChangeDate(LocalDateTime.now());
            byId.get().setSubjectType(subjectDto.getSubjectType());
            byId.get().setSemester(subjectDto.getSemester());
            byId.get().setLanguage(subjectDto.getLanguage());
            byId.get().setAbbreviation(subjectDto.getAbbreviation());
        }
    }

    @Transactional
    public void lockSubjects(){
        for (SubjectEntity b1 : subjectRepository.findAll()) {
            b1.setAccessible(true);
        }
    }

    @Transactional
    public void unlockSubjects(){
        for (SubjectEntity b1 : subjectRepository.findAll()) {
            b1.setAccessible(false);
        }
    }

    @Transactional
    public void lockSubjectById(Long subjectId){
        Optional<SubjectEntity> byId = subjectRepository.findById(subjectId);
        byId.ifPresent(subjectEntity -> subjectEntity.setAccessible(true));
    }

    @Transactional
    public void subscribeForSubject(SubscribeDto subscribeDto){
        Optional<UserEntity> student = userRepository.findById(subscribeDto.getStudentId());
        Optional<SubjectEntity> course = subjectRepository.findById(subscribeDto.getSubjectId());
        SubjectStudentEntity subscription = new SubjectStudentEntity();
        subscription.setSubjectEntity(course.get());
        subscription.setUserEntity(student.get());
        Optional<RoleEntity> role = this.roleRepository.findByRoleName("ROLE_STUDENT");
        if(student.get().getRoles().contains(role.get())){
            if(course.get().isAccessible() && !course.get().getStatus().equals(Status.REFUSED)){
                course.get().getSubscribedUsers().add(subscription);
                subscription.setDateOfRegistration(LocalDateTime.now());
                subscription.setLastModifiedDate(LocalDateTime.now());
                subscription.setStatus(StudentStatus.UNCOMPLETED);
                subscription.setAssessment(Assessment.NotGiven);
                this.subscriptionsRepository.save(subscription);
            }
//            return "Subject " + course.get().getSubjectName() + " is locked!";
        }else{
//            return "User " + student.get().getUsername() + " is not student!";
        }
    }

    @Transactional
    public void unsubscribeFromSubject(SubscribeDto subscribeDto){
        Optional<SubjectStudentEntity> byId =  this.subscriptionsRepository.findBySubjectEntityIdAndUserEntityId(subscribeDto.getSubjectId(), subscribeDto.getStudentId());
        byId.ifPresent(subscriptionsRepository::delete);
    }

    @Transactional
    public SubscribeDto getSubscription(Long subjectId, Long studentId){
        Optional<SubjectStudentEntity> byId =  this.subscriptionsRepository.findBySubjectEntityIdAndUserEntityId(subjectId, studentId);
        if(byId.isPresent()){
            SubscribeDto subscription = new SubscribeDto();
            subscription.setSubjectId(byId.get().getSubjectEntity().getId());
            subscription.setStudentId(byId.get().getUserEntity().getId());
            subscription.setDateOfRegistration(byId.get().getDateOfRegistration());
            subscription.setLastModifiedDate(byId.get().getLastModifiedDate());
            subscription.setSubjectName(byId.get().getSubjectEntity().getSubjectName());
            subscription.setStudentFirstName(byId.get().getUserEntity().getFirstName());
            subscription.setStudentLastName(byId.get().getUserEntity().getLastName());
            subscription.setStatus(byId.get().getStatus());
            subscription.setAssessment(byId.get().getAssessment());
            return subscription;
        }
        return null;
    }

}
