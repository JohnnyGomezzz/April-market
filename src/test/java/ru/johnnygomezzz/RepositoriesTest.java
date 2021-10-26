package ru.johnnygomezzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.johnnygomezzz.models.Category;
import ru.johnnygomezzz.models.Order;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.models.User;
import ru.johnnygomezzz.repositories.CategoryRepository;
import ru.johnnygomezzz.repositories.OrderRepository;
import ru.johnnygomezzz.repositories.ProductRepository;
import ru.johnnygomezzz.repositories.UserRepository;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class RepositoriesTest {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void categoryRepositoryTest() {
        Category category = new Category();
        category.setTitle("Овощи");
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categoriesList = categoryRepository.findAll();

        Assertions.assertEquals(2, categoriesList.size());
        Assertions.assertEquals("Овощи", categoriesList.get(1).getTitle());
    }

    @Test
    public void initCategoryDbTest() {
        List<Category> categoriesList = categoryRepository.findAll();
        Assertions.assertEquals(1, categoriesList.size());
    }

    @Test
    public void productRepositoryTest() {
        Product product = new Product();
        product.setTitle("Горчица");
        entityManager.persist(product);
        entityManager.flush();

        List<Product> productsList = productRepository.findAll();

        Assertions.assertEquals(26, productsList.size());
        Assertions.assertEquals("Горчица", productsList.get(25).getTitle());
    }

    @Test
    public void initProductDbTest() {
        List<Product> productsList = productRepository.findAll();
        Assertions.assertEquals(25, productsList.size());
    }

    @Test
    public void userRepositoryTest() {
        User user = new User();
        user.setUsername("tempUser");
        user.setPassword("111");
        entityManager.persist(user);
        entityManager.flush();

        List<User> usersList = userRepository.findAll();

        Assertions.assertEquals(3, usersList.size());
        Assertions.assertEquals("tempUser", usersList.get(2).getUsername());
        Assertions.assertEquals("111", usersList.get(2).getPassword());
    }

    @Test
    public void initUserDbTest() {
        List<User> usersList = userRepository.findAll();
        Assertions.assertEquals(2, usersList.size());
    }

    @Test
    public void orderRepositoryTest() {
        Order order = new Order();
        order.setAddress("Питер");
        entityManager.persist(order);
        entityManager.flush();

        List<Order> ordersList = orderRepository.findAll();

        Assertions.assertEquals(1, ordersList.size());
        Assertions.assertEquals("Питер", ordersList.get(0).getAddress());
    }

    @Test
    public void initOrderDbTest() {
        List<Order> ordersList = orderRepository.findAll();
        Assertions.assertEquals(0, ordersList.size());
    }
}
