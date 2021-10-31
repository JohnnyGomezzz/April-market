package ru.johnnygomezzz.service;

import org.springframework.stereotype.Component;
import ru.johnnygomezzz.dto.StudentsDto;
import ru.johnnygomezzz.entity.Students;

@Component
public class StudentsConverter {

    public Students fromStudentsDtoToStudent(StudentsDto studentsDto) {
        Students students = new Students();
        students.setId(studentsDto.getId());
        students.setName(studentsDto.getName());
        students.setNickname(studentsDto.getNickname());
        return students;
    }

    public StudentsDto fromStudentToStudentsDto(Students students) {
        return StudentsDto.builder()
                .id(students.getId())
                .name(students.getName())
                .nickname(students.getNickname())
                .build();
    }
}
