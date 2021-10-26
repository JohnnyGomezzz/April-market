package ru.johnnygomezzz;

import ru.johnnygomezzz.entity.Student;
import ru.johnnygomezzz.service.StudentService;

public class MainApp {
    public static void main(String[] args) {
        StudentService studentService = new StudentService();

        Student student = new Student("Евлампий", 100500);
        studentService.saveOrUpdateStudent(student);

        System.out.println(studentService.findStudentById(student.getId()));

        //System.out.println(studentService.findAllStudents());


    }
}
