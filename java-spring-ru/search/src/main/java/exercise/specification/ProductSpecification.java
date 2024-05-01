package exercise.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import exercise.dto.ProductParamsDTO;
import exercise.model.Product;

// BEGIN
@Component
public class ProductSpecification {
    public Specification<Product> build(ProductParamsDTO params) {
        return withTitle(params.getTitleCont())
                .and(withCategory(params.getCategoryId()))
                .and(withPriceLt(params.getPriceLt()))
                .and(withPriceGt(params.getPriceGt()))
                .and(withRatingGt(params.getRatingGt()));
    }

    private Specification<Product> withTitle(String stringTitle) {
        var pattern = ("%" + stringTitle + "%").toLowerCase();
        return (root, query, cb) -> stringTitle == null ? cb.conjunction() : cb.like(cb.lower(root.get("title")), pattern);
    }

    private Specification<Product> withCategory(Long id) {
        return (root, query, cb) -> id == null ? cb.conjunction() : cb.equal(root.get("category").get("id"), id);
    }

    private Specification<Product> withPriceLt(Integer priceLt) {
        return (root, query, cb) -> priceLt == null ? cb.conjunction() : cb.lessThanOrEqualTo(root.get("price"), priceLt);
    }

    private Specification<Product> withPriceGt(Integer priceGt) {
        return (root, query, cb) -> priceGt == null ? cb.conjunction() : cb.greaterThan(root.get("price"), priceGt);
    }

    private Specification<Product> withRatingGt(Double ratingGt) {
        return (root, query, cb) -> ratingGt == null ? cb.conjunction() : cb.greaterThanOrEqualTo(root.get("rating"), ratingGt);
    }
}
// END
