package ru.johnnygomezzz.utils;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import ru.johnnygomezzz.models.OrderItem;
import ru.johnnygomezzz.models.Product;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
@RequiredArgsConstructor
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Cart implements Serializable {
    private static final long serialVersionUID = 2906642554793891381L;

    private List<OrderItem> items;
    private BigDecimal sum;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void addToCart(Product product, Long id) {
        for (OrderItem orderItem : items) {
            if (orderItem.getProduct().getId().equals(id)) {
                orderItem.incrementQuantity();
                recalculate();
                return;
            }
        }
        items.add(new OrderItem(product));
        recalculate();
    }

    public void deleteAll() {
        items.clear();
        recalculate();
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

    public void recalculate() {
        sum = BigDecimal.ZERO;
        for (OrderItem oi : items) {
            sum = sum.add(oi.getPrice());
        }
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (OrderItem oi : items) {
            totalQuantity += oi.getQuantity();
        }
        return totalQuantity;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }
}
