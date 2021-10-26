package ru.johnnygomezzz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.johnnygomezzz.models.Feedback;
import ru.johnnygomezzz.models.Product;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
    List<Feedback> findAllByProduct(Product product);
}
