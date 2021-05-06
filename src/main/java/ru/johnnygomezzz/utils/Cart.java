package ru.johnnygomezzz.utils;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.ProductService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Component
public class Cart {
    private List<Product> items;
    private ProductService productService;

    @PostConstruct
    public void init() {
        items = new ArrayList<>();
    }

    public void deleteById(Long id) {
        for (Product item : items) {
            if (item.getId().equals(id)) {
                items.remove(item);
                break;
            }
        }
    }

    public void deleteAll() {
        items.clear();
    }

    public void addToCart(Product product) {
        items.add(product);
    }

    public List<Product> showAll() {
        return items;
    }
}
