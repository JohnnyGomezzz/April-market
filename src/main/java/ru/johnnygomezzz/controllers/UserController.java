package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.ProductDto;
import ru.johnnygomezzz.dtos.UserDto;
import ru.johnnygomezzz.dtos.UserRegDto;
import ru.johnnygomezzz.error_handling.InvalidDataException;
import ru.johnnygomezzz.error_handling.ResourceNotFoundException;
import ru.johnnygomezzz.models.User;
import ru.johnnygomezzz.services.UserService;

import java.security.Principal;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/me")
    public UserDto getCurrentUsername(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        return new UserDto(currentUser.getUsername(), currentUser.getEmail());
    }

    @PostMapping("/register")
    public void register(@RequestBody UserRegDto userRegDto) {
        userRegDto.setPassword(passwordEncoder.encode(userRegDto.getPassword()));
        userService.createNewUser(userRegDto);
    }
}
