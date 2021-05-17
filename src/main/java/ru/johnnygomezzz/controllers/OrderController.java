package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.OrderDto;
import ru.johnnygomezzz.models.Order;
import ru.johnnygomezzz.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public List<Order> getAllOrders() {
        List<Order> orders = orderService.findAll();
        return orders;
    }
}
