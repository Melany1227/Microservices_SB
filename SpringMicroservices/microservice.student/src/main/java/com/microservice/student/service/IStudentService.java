package com.microservice.student.service;
import com.microservice.student.entities.Student;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface IStudentService {

    List<Student> findAll();
    Student findById(Long id);

    void save(Student student);

    List<Student> findByIdCourse(Long idCourse);
}
