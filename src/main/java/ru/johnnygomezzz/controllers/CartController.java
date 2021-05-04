package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.utils.Cart;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;

    @GetMapping
    public void showCart() {
        cart.showAll();
    }

    @GetMapping("/ping")
    public void ping(@RequestParam Long id) {
        log.info("ping: " + id);
    }

    @GetMapping("/add")
    public void addToCart(@RequestBody Product product) {
        cart.addToCart(product);
    }

    @DeleteMapping("/{id}")
    public void deleteOneProductById(@PathVariable Long id) {
        cart.deleteById(id);
    }

    @DeleteMapping
    public void deleteAllProducts() {
        cart.deleteAll();
    }
}
