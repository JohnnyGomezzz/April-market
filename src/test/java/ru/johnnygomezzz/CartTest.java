package ru.johnnygomezzz;

import ru.johnnygomezzz.models.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.johnnygomezzz.utils.Cart;

import java.math.BigDecimal;

@SpringBootTest(classes = Cart.class)
public class CartTest {
    @Autowired
    private Cart cart;

    @Test
    public void cartFillingTest() {
        for (int i = 0; i < 10; i++) {
            Product product = new Product();
            long productId = i / 2 + 1;
            product.setId(productId);
            product.setPrice(new BigDecimal(100 + productId * 10));
            product.setTitle("Product #" + productId);
            cart.addToCart(product);
        }
        Assertions.assertEquals(5, cart.getItems().size());
        cart.deleteAllById(2L);
        Assertions.assertEquals(4, cart.getItems().size());
        cart.deleteById(1L);
        Assertions.assertEquals(46, cart.getItems().size());
        cart.deleteAll();
        Assertions.assertEquals(0, cart.getItems().size());
    }
}