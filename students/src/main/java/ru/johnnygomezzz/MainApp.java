package ru.johnnygomezzz;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.johnnygomezzz.entity.Student;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

public class MainApp {
    private static SessionFactory factory;

    public static void init() {
        factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void main(String[] args) {
        try {
            init();
            prepareData();
            try (Session session = factory.getCurrentSession()) {
                session.beginTransaction();
                Student student = session.get(Student.class, 112L);
                System.out.println(student);
                session.getTransaction().commit();
            }
        } finally {
            shutdown();
        }
    }

    public static void prepareData() {
        Session session = null;
        try {
            String sql = Files.lines(Paths.get("d:/_java/april-market/students/db.sql")).collect(Collectors.joining(" "));
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.createNativeQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static void shutdown() {
        factory.close();
    }
}
