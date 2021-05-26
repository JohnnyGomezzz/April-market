package ru.johnnygomezzz.dtos;

import lombok.Data;

@Data
public class UserRegDto {
    private String username;
    private String password;
    private String email;

    public UserRegDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
