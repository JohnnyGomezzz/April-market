package ru.johnnygomezzz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.johnnygomezzz.entity.Student;
import ru.johnnygomezzz.repository.StudentRepository;
import ru.johnnygomezzz.service.StudentService;

public class MainApp {
    private static StudentService studentService = new StudentService();

    private static SessionFactory factory;

    public static void init() {
        InitApp.forcePrepareData();
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
        Session session = null;
    }

    public static void main(String[] args) {
        try {
            init();
            studentService.findStudentById(4L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    public static void shutdown() {
        factory.close();
    }
}
