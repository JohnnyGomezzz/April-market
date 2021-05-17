package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.models.Order;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class OrderDto {
    private CartDto cartDto;
    private Long orderId;
    private int quantity;
    private BigDecimal price;

    public OrderDto(Order order) {
        this.orderId = order.getId();
        this.quantity = order.getQuantity();
        this.price = order.getPrice();
    }

    public BigDecimal getPrice() {
        price = cartDto.getSum();
        return price;
    }
}
