package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.johnnygomezzz.dtos.CartDto;
import ru.johnnygomezzz.dtos.OrderDto;
import ru.johnnygomezzz.models.Order;
import ru.johnnygomezzz.models.OrderItem;
import ru.johnnygomezzz.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        orderRepository.save(order);
        return new OrderDto(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}

