package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.models.Order;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String date;
    private String description;
    private BigDecimal price;
    private String address;
    private Long phone;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.date = order.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        this.description = order.getItems().stream().map(o -> o.getProduct().getTitle() + " x"
                + o.getQuantity()).collect(Collectors.joining(", "));
        this.price = order.getPrice();
        this.address = order.getAddress();
        this.phone = order.getPhone();
    }
}
