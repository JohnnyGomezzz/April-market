package ru.johnnygomezzz.repository;

import ru.johnnygomezzz.entity.Student;

import java.util.List;

public class StudentRepository extends AbstractRepository<Student, Long> {

    public StudentRepository(Class<Student> typeClass, Class<Long> longClass) {
        super(typeClass, longClass);
    }

    public Student findStudent(Long id) {
        return findById(id);
    }

    public List<Student> findStudents() {
        return findAll();
    }

    public void saveUpdate(Student student) {
        saveOrUpdate(student);
    }

    public void deleteStudent(Student student) {
        delete(student);
    }
}
