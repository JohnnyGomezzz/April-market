package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.utils.Cart;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {
    private List<ProductDto> items;
    private BigDecimal sum;

    public CartDto(Cart cart) {
        List<ProductDto> list = new ArrayList<>();
        for (Product product : cart.getItems()) {
            ProductDto productDto = new ProductDto(product);
            list.add(productDto);
        }
        this.items = list;
        this.sum = cart.getSum();
    }
}
