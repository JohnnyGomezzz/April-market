package ru.johnnygomezzz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.johnnygomezzz.entity.Students;

public interface StudentsRepository extends JpaRepository<Students, Integer> {
    Students findByNickname(String nickname);
}
