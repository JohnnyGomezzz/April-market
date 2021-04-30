package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.ProductService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("api/v1/products")
    @ResponseBody
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("api/v1/products/{id}")
    @ResponseBody
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }
}
