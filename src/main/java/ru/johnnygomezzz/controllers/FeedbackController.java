package ru.johnnygomezzz.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.johnnygomezzz.dtos.FeedbackDto;
import ru.johnnygomezzz.models.Product;
import ru.johnnygomezzz.services.FeedbackService;
import ru.johnnygomezzz.services.UserService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/feedback")
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final UserService userService;

    @GetMapping
    @Transactional
    public List<FeedbackDto> getAllFeedbackForCurrentProduct(Product product) {
        return feedbackService.findAllByProduct(product).stream().map(FeedbackDto::new).collect(Collectors.toList());
    }

    @PostMapping()
    public void createNewFeedback(Product product, @RequestParam(name = "message") String message) {
        feedbackService.createFeedbackForCurrentProduct(product, message);
    }
}
