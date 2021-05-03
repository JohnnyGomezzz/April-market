package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.ProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getOneProductById(@PathVariable Long id) {
        return productService.findById(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteOneProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    //на свой страх и риск!!!
    @DeleteMapping
    public void deleteAllProducts() {
        productService.deleteAll();
    }

    @PostMapping
    public Product createNewProduct(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping
    public Product modifyProduct(@RequestBody Product product) {
        return productService.save(product);
    }
}
