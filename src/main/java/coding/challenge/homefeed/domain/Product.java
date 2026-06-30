package coding.challenge.homefeed.domain;

import java.math.BigDecimal;

public class Product {

    private String id;
    private String name;
    private BigDecimal price;
    private String imageUrl;

    public Product(String id, String name, BigDecimal price, String imageUrl) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    
}
