package ru.johnnygomezzz.controller;

import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dto.StudentsDto;
import ru.johnnygomezzz.exceptions.ValidationException;
import ru.johnnygomezzz.service.StudentsService;

import java.util.List;

@Log
@RestController
@AllArgsConstructor
@RequestMapping("/students")
public class StudentsController {

    private final StudentsService studentsService;

    @PostMapping("/save")
    public StudentsDto saveUsers(@RequestBody StudentsDto studentsDto) throws ValidationException {
        log.info("Handling save students: " + studentsDto);
        return studentsService.saveStudent(studentsDto);
    }

    @GetMapping("/findAll")
    public List<StudentsDto> findAllUsers() {
        log.info("Handling find all users request");
        return studentsService.findAll();
    }

    @GetMapping("/findByNickname")
    public StudentsDto findByNickname(@RequestParam String nickname) {
        log.info("Handling find by nickname request: " + nickname);
        return studentsService.findByNickname(nickname);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteStudents(@PathVariable Integer id) {
        log.info("Handling delete student request: " + id);
        studentsService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }
}
