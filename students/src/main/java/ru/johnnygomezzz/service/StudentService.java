package ru.johnnygomezzz.service;

import ru.johnnygomezzz.entity.Student;
import ru.johnnygomezzz.repository.AbstractRepository;
import ru.johnnygomezzz.repository.StudentRepository;

import java.util.List;

public class StudentService {
    private final StudentRepository studentRepository = new StudentRepository(Student.class, Long.class);

    public Student findStudentById(Long id) {
        return studentRepository.findStudent(id);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findStudents();
    }

    public void saveOrUpdateStudent(Student student) {
        studentRepository.saveUpdate(student);
    }
}
