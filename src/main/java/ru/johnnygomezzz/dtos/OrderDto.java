package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.models.Order;
import ru.johnnygomezzz.models.OrderItem;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private CartDto cartDto;
    private Long orderId;
    private int quantity;
    private BigDecimal price;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.quantity = cartDto.getTotalQuantity();
        this.price = cartDto.getSum();
    }
}
