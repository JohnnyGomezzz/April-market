package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.soap.products.Product;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductSoapDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;

    public ProductSoapDto(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.price = product.getPrice();
        this.categoryTitle = product.getCategory();
    }
}
