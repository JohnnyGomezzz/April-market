package ru.johnnygomezzz.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.johnnygomezzz.models.*;
import ru.johnnygomezzz.repositories.FeedbackRepository;
import ru.johnnygomezzz.repositories.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;
    private final UserRepository userRepository;
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

    @Transactional
    public void save (Feedback feedback) {
        user = userRepository.findById(feedback.getUser().getId()).get();
        List<Order> orders = user.getOrders();
        for (Order o : orders) {
            List<OrderItem> items = o.getItems();
            for(OrderItem oi: items){
                if (oi.getProduct().getId().equals(feedback.getProduct().getId())) {
                    feedbackRepository.save(feedback);
                    return;
                }
            }
        }
    }
}

