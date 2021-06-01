package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.johnnygomezzz.error_handling.ResourceNotFoundException;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.utils.Cart;

@Service
@RequiredArgsConstructor
public class CartService {
    private final Cart cart;
    private final ProductService productService;

    public void addToCart(Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id + " (add to cart)"));
        cart.addToCart(product);
    }
}
