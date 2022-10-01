package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @InjectMocks
    StudentService studentService;

    @Mock
    StudentRepository studentRepository;

    @Test
    public void studentServiceCreateValid(){
        Long generatedId = 1L;

        StudentEntity fakeEntity = new StudentEntity();
        fakeEntity.setId(generatedId);
        fakeEntity.setFirstName("Adela");
        fakeEntity.setLastName("Hraskova");

        when(studentRepository.save(any(StudentEntity.class))).thenReturn(fakeEntity);

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Adela");
        studentDto.setLastName("Hraskova");

        Long id = studentService.createStudent(studentDto);

        assertEquals(generatedId, id);
        verify(studentRepository, times(1)).save(any());
    }

}