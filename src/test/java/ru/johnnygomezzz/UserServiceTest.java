package ru.johnnygomezzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import ru.johnnygomezzz.models.User;
import ru.johnnygomezzz.repositories.UserRepository;
import ru.johnnygomezzz.services.UserService;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findOneUserTest() {
        User userFromDB = new User();
        userFromDB.setUsername("John");
        userFromDB.setEmail("john@mail.ru");

        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findByUsername("John");

        User userJohn = userService.findByUsername("John").get();
        Assertions.assertNotNull(userJohn);
        Assertions.assertEquals("john@mail.ru", userJohn.getEmail());
        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
    }

    @Test
    public void createOneUserTest() {
//        User userFromDB = new User();
//        userFromDB.setUsername("John");
//        userFromDB.setEmail("john@mail.ru");
//
//        Mockito.doReturn(Optional.of(userFromDB))
//                .when(userRepository)
//                .findByUsername("John");
//
//        User userJohn = userService.findByUsername("John").get();
//        Assertions.assertNotNull(userJohn);
//        Assertions.assertEquals("john@mail.ru", userJohn.getEmail());
//        Mockito.verify(userRepository, Mockito.times(1)).findByUsername(ArgumentMatchers.eq("John"));
    }
}
