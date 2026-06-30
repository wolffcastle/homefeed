package coding.challenge.homefeed.adapter.in.web.dto;

import java.util.List;

import coding.challenge.homefeed.domain.Product;
/**
 * 
 * ProductTeaserModuleDto
 * Delivers concrete JSON structure for the product teaser module in the homefeed response.
 * @param type
 * @param title
 * @param products
 */
public class ProductTeaserModuleDto implements HomefeedModuleDto {
        String type;
        String title;
        List<ProductCardDto> products;

    public ProductTeaserModuleDto(String title, List<ProductCardDto> products) {
        this.type = "product_teaser";
        this.title = title;
        this.products = products;
    }

    public String getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public List<ProductCardDto> getProducts() {
        return products;
    }

    @Override
    public String type() {
        return type;
    }
    
}
