package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.johnnygomezzz.dtos.OrderDto;
import ru.johnnygomezzz.dtos.ProductDto;
import ru.johnnygomezzz.error_handling.ResourceNotFoundException;
import ru.johnnygomezzz.models.Category;
import ru.johnnygomezzz.models.Order;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.repositories.OrderRepository;
import ru.johnnygomezzz.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createNewOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setQuantity(orderDto.getQuantity());
        order.setPrice(orderDto.getPrice());
        orderRepository.save(order);
        return new OrderDto(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

//    @Transactional
//    public ProductDto updateProduct(ProductDto productDto) {
//        Product product = findById(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product doesn't exists id: " + productDto.getId() + " (for update)"));
//        product.setPrice(productDto.getPrice());
//        product.setTitle(productDto.getTitle());
//        Category category = categoryService.findByTitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("Category doesn't exists product.categoryTitle = " + productDto.getCategoryTitle() + " (Product creation)"));
//        product.setCategory(category);
//        return new ProductDto(product);
//    }

    public void deleteById(Long id) {
        orderRepository.deleteById(id);
    }
}

