package ru.johnnygomezzz.service;

import ru.johnnygomezzz.dto.StudentsDto;
import ru.johnnygomezzz.exceptions.ValidationException;

import java.util.List;

public interface StudentsService {

    StudentsDto saveStudent(StudentsDto studentsDto) throws ValidationException;

    void deleteStudent(Integer studentId);

    StudentsDto findByNickname(String nickname);

    List<StudentsDto> findAll();
}
