package ru.johnnygomezzz.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.johnnygomezzz.models.Feedback;
import ru.johnnygomezzz.models.Order;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class FeedbackDto {
    private String date;
    private String username;
    private String message;

    public FeedbackDto(Feedback feedback) {
        this.date = feedback.getCreatedAt().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
        this.username = feedback.getUser().getUsername();
        this.message = feedback.getMessage();
    }
}
