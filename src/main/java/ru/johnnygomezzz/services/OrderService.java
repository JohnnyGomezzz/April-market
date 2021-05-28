package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.johnnygomezzz.dtos.OrderDto;
import ru.johnnygomezzz.models.Order;
import ru.johnnygomezzz.models.OrderItem;
import ru.johnnygomezzz.models.User;
import ru.johnnygomezzz.repositories.OrderRepository;
import ru.johnnygomezzz.utils.Cart;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final Cart cart;

    public List<Order> findAllByUser(User user) {
        return orderRepository.findAllByUser(user);
    }

    public Order createOrderForCurrentUser(User user, String address, Long phone) {
        Order order = new Order();
        order.setUser(user);
        order.setPrice(cart.getSum());
        // todo распутать этот кусок
        order.setItems(cart.getItems());
        for (OrderItem oi : cart.getItems()) {
            oi.setOrder(order);
        }
        order.setAddress(address);
        order.setPhone(phone);
        order = orderRepository.save(order);
        cart.deleteAll();
        return order;
    }
}

