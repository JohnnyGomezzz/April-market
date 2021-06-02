package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.soap.products.ProductEntity;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductSoapDto {
    private Long id;
    private String title;
    private BigDecimal price;
    private String categoryTitle;

    public ProductSoapDto(ProductEntity productEntity) {
        this.id = productEntity.getId();
        this.title = productEntity.getTitle();
        this.price = productEntity.getPrice();
        this.categoryTitle = productEntity.getCategory();
    }
}
