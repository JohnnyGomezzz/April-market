package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.johnnygomezzz.models.*;
import ru.johnnygomezzz.repositories.FeedbackRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private Product product;
    private User user;

    public List<Feedback> findAllByProduct(Product product) {
        return feedbackRepository.findAllByProduct(product);
    }

    public Feedback createFeedbackForCurrentProduct(Product product, String message) {
        Feedback feedback = new Feedback();
        feedback.setUser(user);
        feedback.setProduct(product);
        feedback.setMessage(message);
        return feedback;
    }
}

