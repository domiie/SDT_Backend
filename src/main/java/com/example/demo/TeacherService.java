package com.example.demo;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    private static TeacherDto mapToCustomerDto(TeacherEntity teacherEntity){
        TeacherDto teacherDto = new TeacherDto();

        teacherDto.setId(teacherEntity.getId());
        teacherDto.setFirstName(teacherEntity.getFirstName());
        teacherDto.setLastName(teacherEntity.getLastName());
        teacherDto.setPassword(teacherEntity.getPassword());
        teacherDto.setEmail(teacherEntity.getEmail());
        teacherDto.setPhone(teacherEntity.getPhone());
        teacherDto.setKeyword(teacherEntity.getKeyword());

        return teacherDto;
    }

    @Transactional
    public Long createTeacher(TeacherDto teacher){
        TeacherEntity teacherEntity = new TeacherEntity();

        teacherEntity.setFirstName(teacher.getFirstName());
        teacherEntity.setLastName(teacher.getLastName());
        teacherEntity.setPassword(teacher.getPassword());
        teacherEntity.setEmail(teacher.getEmail());
        teacherEntity.setPhone(teacher.getPhone());
        teacherEntity.setKeyword(teacher.getKeyword());


        this.teacherRepository.save(teacherEntity);

        return teacherEntity.getId();
    }

    @Transactional
    public List<TeacherDto> getTeachers(String lastname){
        List<TeacherDto> teachers = new LinkedList<>();
        for(TeacherEntity c1 : teacherRepository.findAll()){
            TeacherDto c2 = mapToCustomerDto(c1);
            teachers.add(c2);
        }
        return teachers;
    }

    @Transactional
    public TeacherDto getTeacher(Long teacherId){
        Optional<TeacherEntity> byId = teacherRepository.findById(teacherId);
        if(byId.isPresent()){
            return  mapToCustomerDto(byId.get());
        }
        return null;
    }


    @Transactional
    public void updateTeacher(Long teacherId, Teacher teacher){
        Optional<TeacherEntity> byId = teacherRepository.findById(teacherId);

        if (byId.isPresent()) {
            byId.get().setFirstName(teacher.getFirstName());
            byId.get().setLastName(teacher.getLastName());
            byId.get().setPassword(teacher.getPassword());
            byId.get().setEmail(teacher.getEmail());
            byId.get().setPhone(teacher.getPhone());
            byId.get().setKeyword(teacher.getKeyword());
        }

    }

    @Transactional
    public void deleteTeacher(Long teacherId){
        Optional<TeacherEntity> byId = teacherRepository.findById(teacherId);

        if (byId.isPresent()) {
            teacherRepository.delete(byId.get());
        }
    }

}
