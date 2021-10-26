package ru.johnnygomezzz.service;

import org.hibernate.SessionFactory;
import ru.johnnygomezzz.entity.Student;
import ru.johnnygomezzz.repository.StudentRepository;

public class StudentService {
    private StudentRepository studentRepository = new StudentRepository();

    public Student findStudentById(Long id) {
        return studentRepository.findById(id);
    }
}
