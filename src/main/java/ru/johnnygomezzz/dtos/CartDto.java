package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.utils.Cart;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class CartDto {
    private List<ProductDto> productDtoList;

    public CartDto(Cart cart) {
        List<ProductDto> list = new ArrayList<>();
        for (Product product : cart.getItems()) {
            ProductDto productDto = new ProductDto(product);
            list.add(productDto);
        }
        this.productDtoList = list;
    }

    public List<ProductDto> showAll() {
        return productDtoList;
    }
}
