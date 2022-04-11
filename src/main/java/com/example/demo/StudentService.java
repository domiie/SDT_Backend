package com.example.demo;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    private static StudentDto mapToCustomerDto(StudentEntity studentEntity){
        StudentDto studentDto = new StudentDto();

        studentDto.setId(studentDto.getId());
        studentDto.setFirstName(studentDto.getFirstName());
        studentDto.setLastName(studentDto.getLastName());
        studentDto.setPassword(studentDto.getPassword());
        studentDto.setEmail(studentDto.getEmail());
        studentDto.setPhone(studentDto.getPhone());
        studentDto.setKeyword(studentDto.getKeyword());

        return studentDto;
    }

    @Transactional
    public Long createStudent(StudentDto student){
        StudentEntity studentEntity = new StudentEntity();

        studentEntity.setFirstName(student.getFirstName());
        studentEntity.setLastName(student.getLastName());
        studentEntity.setPassword(student.getPassword());
        studentEntity.setEmail(student.getEmail());
        studentEntity.setPhone(student.getPhone());
        studentEntity.setKeyword(student.getKeyword());


        this.studentRepository.save(studentEntity);

        return studentEntity.getId();
    }

    @Transactional
    public List<StudentDto> getStudents(String lastname){
        List<StudentDto> students = new LinkedList<>();
        for(StudentEntity c1 : studentRepository.findAll()){
            StudentDto c2 = mapToCustomerDto(c1);
            students.add(c2);
        }
        return students;
    }

    @Transactional
    public StudentDto getStudent(Long studentId){
        Optional<StudentEntity> byId = studentRepository.findById(studentId);
        if(byId.isPresent()){
            return  mapToCustomerDto(byId.get());
        }
        return null;
    }


    @Transactional
    public void updateStudent(Long studentId, Student student){
        Optional<StudentEntity> byId = studentRepository.findById(studentId);

        if (byId.isPresent()) {
            byId.get().setFirstName(student.getFirstName());
            byId.get().setLastName(student.getLastName());
            byId.get().setPassword(student.getPassword());
            byId.get().setEmail(student.getEmail());
            byId.get().setPhone(student.getPhone());
            byId.get().setKeyword(student.getKeyword());
        }

    }

    @Transactional
    public void deleteStudent(Long studentId){
        Optional<StudentEntity> byId = studentRepository.findById(studentId);

        if (byId.isPresent()) {
            studentRepository.delete(byId.get());
        }
    }

}
