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
    private StudentService studentService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    public void studentServiceCreateValid(){
        Long generatedId = 1L;

        StudentEntity fakeEntity = new StudentEntity();
        fakeEntity.setId(generatedId);
        fakeEntity.setFirstName("Adela");
        fakeEntity.setLastName("Hraskova");
        fakeEntity.setEmail("ahoj123@gmail.com");
        fakeEntity.setKeyword("hauko");
        fakeEntity.setPhone("0915182404");

        when(studentRepository.save(any(StudentEntity.class))).thenReturn(fakeEntity);

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Adela");
        studentDto.setLastName("Hraskova");
        studentDto.setEmail("ahoj123@gmail.com");
        studentDto.setKeyword("hauko");
        studentDto.setPhone("0915182404");

        Long id = studentService.createStudent(studentDto);

        assertEquals(generatedId, id);
        verify(studentRepository, times(1)).save(any());
    }

    @Test
    public void studentServiceCreateFail(){
        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Adela");
        studentDto.setLastName("Hraskova");
        studentDto.setEmail("ahoj123@gmail.com");
        studentDto.setKeyword("hauko");
        studentDto.setPhone("0915182404");

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> studentService.createStudent(studentDto));
        assertEquals("Meno alebo Priezvisko sú prázdne", exception.getMessage());

        verify(studentRepository, times(0)).save(any());
    }

    @Test
    public void studentServiceDbFailedWrite(){
        when(studentRepository.save(any(StudentEntity.class))).thenThrow(RuntimeException.class);

        StudentDto studentDto = new StudentDto();
        studentDto.setFirstName("Adela");
        studentDto.setLastName("Hraskova");
        studentDto.setEmail("ahoj123@gmail.com");
        studentDto.setKeyword("hauko");
        studentDto.setPhone("0915182404");

        RuntimeException exception = assertThrows(RuntimeException.class, () -> studentService.createStudent(studentDto));

        verify(studentRepository, times(1)).save(any());
    }

}