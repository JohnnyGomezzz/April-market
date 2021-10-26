package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.FeedbackDto;
import ru.johnnygomezzz.models.Feedback;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.FeedbackService;
import ru.johnnygomezzz.services.ProductService;
import ru.johnnygomezzz.services.UserService;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final UserService userService;
    private final ProductService productService;

    @GetMapping
    @Transactional
    public List<FeedbackDto> getAllFeedbackForCurrentProduct(@RequestParam Long productId) {
        Product product = productService.findById(productId).get();
        return feedbackService.findAllByProduct(product).stream().map(FeedbackDto::new).collect(Collectors.toList());
    }

    @PostMapping
    public void createNewFeedback(Principal principal, @RequestParam(name = "message") String message,
                                  @RequestParam(name = "prodId") Long productId) {
        Feedback feedback = new Feedback();
        feedback.setUser(userService.findByUsername(principal.getName()).get());
        feedback.setProduct(productService.findById(productId).get());
        feedback.setMessage(message);
        feedbackService.save(feedback);
    }
}
