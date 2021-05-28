package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.aop.AppServicesAspect;
import ru.johnnygomezzz.dtos.UserDto;
import ru.johnnygomezzz.dtos.UserRegDto;
import ru.johnnygomezzz.error_handling.ResourceNotFoundException;
import ru.johnnygomezzz.models.User;
import ru.johnnygomezzz.services.UserService;

import java.security.Principal;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticsController {
    private final AppServicesAspect appServicesAspect;

    @GetMapping
    public HashMap<String, Long> getServicesStatistics() {
        return appServicesAspect.getClassDuration();
    }
}
