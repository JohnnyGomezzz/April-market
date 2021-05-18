package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.OrderDto;
import ru.johnnygomezzz.dtos.ProductDto;
import ru.johnnygomezzz.error_handling.InvalidDataException;
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

    @GetMapping("/place")
    public OrderDto placeOrder(@RequestBody @Validated OrderDto orderDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        return orderService.createNewOrder(orderDto);
    }
}
