package ru.johnnygomezzz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnnygomezzz.models.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
