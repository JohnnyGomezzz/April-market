package ru.johnnygomezzz.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.johnnygomezzz.entity.Student;
import ru.johnnygomezzz.util.SessionFactoryUtil;

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
//
//    public void delete(Student obj) {
//        try (Session session = factory.getCurrentSession()) {
//            session.beginTransaction();
//            session.delete(obj);
//            session.getTransaction().commit();
//        }
//    }
}
