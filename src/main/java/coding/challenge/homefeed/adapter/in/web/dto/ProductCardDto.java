package coding.challenge.homefeed.adapter.in.web.dto;

import java.math.BigDecimal;

/**
 * 
 * ProductCardDto
 *  Delivers concrete JSON structure for the product card in the homefeed response.  
 */
public class ProductCardDto {
    String id;
    String name;
    BigDecimal price;
    String imageUrl;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public String getImageUrl() {
        return imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}