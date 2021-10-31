package ru.johnnygomezzz.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.johnnygomezzz.dto.StudentsDto;
import ru.johnnygomezzz.entity.Students;
import ru.johnnygomezzz.exceptions.ValidationException;
import ru.johnnygomezzz.repository.StudentsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DefaultStudentsService implements StudentsService {
    private final StudentsRepository studentsRepository;
    private final StudentsConverter studentsConverter;

    private void validateStudentsDto(StudentsDto studentsDto) throws ValidationException {
        if (isNull(studentsDto)) {
            throw new ValidationException("Object student is null");
        }
        if (isNull(studentsDto.getNickname()) || studentsDto.getNickname().isEmpty()) {
            throw new ValidationException("Nickname is empty");
        }
    }

    @Override
    public StudentsDto saveStudent(StudentsDto studentsDto) throws ValidationException {
        validateStudentsDto(studentsDto);
        Students savedStudents = studentsRepository.save(studentsConverter.fromStudentsDtoToStudent(studentsDto));
        return studentsConverter.fromStudentToStudentsDto(savedStudents);
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentsRepository.deleteById(studentId);
    }

    @Override
    public StudentsDto findByNickname(String nickname) {
        Students students = studentsRepository.findByNickname(nickname);
        if (students != null) {
            return studentsConverter.fromStudentToStudentsDto(students);
        }
        return null;
    }

    @Override
    public List<StudentsDto> findAll() {
        return studentsRepository.findAll()
                .stream()
                .map(studentsConverter::fromStudentToStudentsDto)
                .collect(Collectors.toList());
    }

    private boolean isNull(Object o) {
        return false;
    }
}
