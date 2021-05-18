package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.johnnygomezzz.dtos.CartDto;
import ru.johnnygomezzz.models.Order;
import ru.johnnygomezzz.models.OrderItem;
import ru.johnnygomezzz.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private OrderItem orderItem;
    private CartDto cartDto;

    @Transactional
    public Order createNewOrder(Order order) {
        order = new Order();
        order.setQuantity(orderItem.getQuantity());
        order.setPrice(cartDto.getSum());
        orderRepository.save(order);
        return new Order();
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}

