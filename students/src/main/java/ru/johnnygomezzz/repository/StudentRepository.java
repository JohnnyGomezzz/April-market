package ru.johnnygomezzz.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.johnnygomezzz.entity.Student;

import java.util.List;

public class StudentRepository {
    private SessionFactory factory;

    public Student findById(Long id) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Student student = session.get(Student.class, id);
            session.getTransaction().commit();
            return student;
        }
    }

    public List<Student> findAll() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            List<Student> students = (List<Student>)session.get("from " + Student.class.getSimpleName(), List.class);
            session.getTransaction().commit();
            return students;
        }
    }

    public Student saveOrUpdate(Student obj) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(obj);
            session.getTransaction().commit();
            return obj;
        }
    }

    public void delete(Student obj) {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.delete(obj);
            session.getTransaction().commit();
        }
    }
}
