package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.OrderDto;
import ru.johnnygomezzz.models.User;
import ru.johnnygomezzz.services.OrderService;
import ru.johnnygomezzz.services.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    @Transactional
    public List<OrderDto> getAllOrdersForCurrentUser(Principal principal) {
        User user = userService.findByUsername(principal.getName()).get();
        return orderService.findAllByUser(user).stream().map(OrderDto::new).collect(Collectors.toList());
    }

    @PostMapping()
    public void createNewOrder(Principal principal, @RequestParam(name = "address") String address, @RequestParam(name = "phone") String phone) {
        User user = userService.findByUsername(principal.getName()).get();
        orderService.createOrderForCurrentUser(user, address, phone);
    }
}
