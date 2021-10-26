package ru.johnnygomezzz.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import ru.johnnygomezzz.services.CategoryService;

@Endpoint
@RequiredArgsConstructor
public class CategoryEndpoint {
    private static final String NAMESPACE_URI = "http://www.johnnygomezzz.ru/categories";
    private final CategoryService categoryService;
}
