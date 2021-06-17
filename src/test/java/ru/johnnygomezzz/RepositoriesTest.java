package ru.johnnygomezzz;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.johnnygomezzz.models.Category;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.models.User;
import ru.johnnygomezzz.repositories.CategoryRepository;
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
    public void initUserDbTest() {
        List<User> usersList = userRepository.findAll();
        Assertions.assertEquals(2, usersList.size());
    }
}
