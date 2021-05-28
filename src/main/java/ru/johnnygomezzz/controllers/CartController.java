package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.CartDto;
import ru.johnnygomezzz.dtos.ProductDto;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.ProductService;
import ru.johnnygomezzz.utils.Cart;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {
    private final Cart cart;
    private final ProductService productService;

    @GetMapping
    public CartDto getCart() {
        return new CartDto(cart);
    }

    @GetMapping("/add/{productId}")
    public void addToCart(@PathVariable(name = "productId") Long id) {
        Optional<Product> product = productService.findById(id);
        cart.addToCart(id);
    }

    @GetMapping("/delete")
    public void deleteOneProductById(@RequestParam Long id) {
        cart.deleteById(id);
    }

    @GetMapping("/deleteall")
    public void deleteAllProductsById(@RequestParam Long id) {
        cart.deleteAllById(id);
    }

    @GetMapping("/clear")
    public void clearCart() {
        cart.deleteAll();
    }

    @GetMapping("/quantity")
    public int getCartQuantity() {
        return cart.getTotalQuantity();
    }
}
