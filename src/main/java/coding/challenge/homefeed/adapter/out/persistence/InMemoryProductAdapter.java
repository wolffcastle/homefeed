package coding.challenge.homefeed.adapter.out.persistence;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import coding.challenge.homefeed.application.port.out.LoadRecommendedProductsPort;
import coding.challenge.homefeed.domain.Product;

@Component
public class InMemoryProductAdapter implements LoadRecommendedProductsPort {

    public List<Product> PRODUCTS = List.of(
            new Product("xyid-1001", "Relaxed Linen Shirt", new BigDecimal("49.90"), "https://cdn.example.com/products/xyid-1001.jpg"),
            new Product("xyid-1002", "Everyday Sneaker", new BigDecimal("89.00"), "https://cdn.example.com/products/xyid-1002.jpg"),
            new Product("xyid-1003", "Canvas Bag", new BigDecimal("29.90"), "https://cdn.example.com/products/xyid-1003.jpg"),
            new Product("xyid-1004", "Lightweight Jacket", new BigDecimal("119.00"), "https://cdn.example.com/products/xyid-1004.jpg")
    );

    @Override
    public List<Product> loadRecommendedProducts(String userId, int limit) {
        return PRODUCTS.stream()
                .limit(limit)
                .toList();
    }

}
