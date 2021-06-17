package ru.johnnygomezzz;

import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.test.DemoBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.johnnygomezzz.utils.Cart;

import java.math.BigDecimal;

//@SpringBootTest(classes = DemoBean.class)
//public class CartTest {
//    @Autowired
//    private Cart cart;
//
//    @Test
//    public void cartFillingTest() {
//        for (int i = 0; i < 10; i++) {
//            Product product = new Product();
//            long bookId = i / 2 + 1;
//            product.setId(bookId);
//            product.setPrice(new BigDecimal(100 + bookId * 10));
//            product.setTitle("Book #" + bookId);
//            cart.addOrIncrement(book);
//        }
//        Assertions.assertEquals(5, cart.getItems().size());
//        cart.clear();
//        Assertions.assertEquals(0, cart.getItems().size());
//    }
//}