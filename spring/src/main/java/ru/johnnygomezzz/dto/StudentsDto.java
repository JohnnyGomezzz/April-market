package ru.johnnygomezzz.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StudentsDto {

    private Integer id;
    private String name;
    private String nickname;
    private int age;
}
