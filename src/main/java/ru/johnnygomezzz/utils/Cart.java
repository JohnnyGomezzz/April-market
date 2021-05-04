package ru.johnnygomezzz.utils;

import org.springframework.stereotype.Component;
import ru.johnnygomezzz.models.Product;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Cart {
    private List<Product> items;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void deleteById(Long id) {
        items.remove(id);
    }

    public void deleteAll() {
        items.clear();
    }

    public void addToCart(Product product) {
        items.add(product);
    }

    public void showAll() {
        for (int i = 0; i < items.size(); i++) {
            items.get(i);
        }
    }
}
