package ru.johnnygomezzz.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.MultiValueMap;
import ru.johnnygomezzz.models.Product;

public class ProductSpecifications {
    private static Specification<Product> priceGreaterOrEqualsThan(int minPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice);
    }

    private static Specification<Product> priceLesserOrEqualsThan(int maxPrice) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }

    private static Specification<Product> titleLike(String titlePart) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), String.format("%%%s%%", titlePart));
    }
    public static Specification<Product> categoryIs(Long categoryId) {
        return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("category").get("id"), categoryId);
    }

    public static Specification<Product> build(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey("min_price") && !params.getFirst("min_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(Integer.parseInt(params.getFirst("min_price"))));
        }
        if (params.containsKey("max_price") && !params.getFirst("max_price").isBlank()) {
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(Integer.parseInt(params.getFirst("max_price"))));
        }
        if (params.containsKey("title") && !params.getFirst("title").isBlank()) {
            spec = spec.and(ProductSpecifications.titleLike(params.getFirst("title")));
        }
//        if (params.containsKey("categories_ids") && params.get("categories_ids").size() > 0) {
//            Specification<Product> categoriesSpec = Specification.where(null);
//            for (int i = 0; i < params.get("categories_ids").size(); i++) {
//                Long categoryId = Long.parseLong(params.get("categories_ids").get(i));
//                categoriesSpec = categoriesSpec.or(ProductSpecifications.categoryIs(categoryId));
//            }
//            spec = spec.and(categoriesSpec);
//        }
        return spec;
    }
}

