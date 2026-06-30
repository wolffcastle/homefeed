package coding.challenge.homefeed.application.port.out;

import java.util.List;

import coding.challenge.homefeed.domain.Product;

/**
 * Port for loading recommended products for a user.
 * Describes what the applications needs from the outside world, but not how it should be done.
 * 
 */
public interface LoadRecommendedProductsPort {
    List<Product> loadRecommendedProducts(String userId, int limit);
}
