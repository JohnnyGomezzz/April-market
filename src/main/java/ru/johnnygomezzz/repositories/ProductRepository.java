package ru.johnnygomezzz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnnygomezzz.models.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
