package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.ProductDto;
import ru.johnnygomezzz.error_handling.InvalidDataException;
import ru.johnnygomezzz.error_handling.ResourceNotFoundException;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.repositories.specifications.ProductSpecifications;
import ru.johnnygomezzz.services.ProductService;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(@RequestParam MultiValueMap<String, String> params,
                                           @RequestParam(name = "p", defaultValue = "1") int page) {
        if (page < 1) {
            page = 1;
        }
        return productService.findAll(ProductSpecifications.build(params), page, 5);
    }

    @GetMapping("/{id}")
    public ProductDto getOneProductById(@PathVariable Long id) {
        Product product = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + id));
        return new ProductDto(product);
    }

    @DeleteMapping("/{id}")
    public void deleteOneProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody @Validated ProductDto productDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new InvalidDataException(bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.toList()));
        }
        return productService.createNewProduct(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.updateProduct(productDto);
    }
}
