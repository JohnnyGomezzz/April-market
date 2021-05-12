package ru.johnnygomezzz.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.johnnygomezzz.error_handling.ResourceNotFoundException;
import ru.johnnygomezzz.models.OrderItem;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.ProductService;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Cart {
    private List<OrderItem> items;
    private final ProductService productService;
    private BigDecimal sum;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void deleteById(Long id) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.decrementQuantity();
                if (orderItem.getQuantity() == 0) {
                    deleteAllById(id);
                }
                recalculate();
                return;
            }
        }
    }

    public void deleteAllById(Long id) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(id)) {
                items.remove(orderItem);
                recalculate();
                return;
            }
        }
    }

    public void deleteAll() {
        items.clear();
        recalculate();
    }

    public void addToCart(Long id) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }

        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        items.add(new OrderItem(product));
        recalculate();
    }

    private void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItem oi : items) {
            sum = sum.add(oi.getPrice());
        }
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
