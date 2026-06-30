package coding.challenge.homefeed.adapter.in.web.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import coding.challenge.homefeed.adapter.in.web.dto.HomefeedModuleDto;
import coding.challenge.homefeed.adapter.in.web.dto.ProductCardDto;
import coding.challenge.homefeed.adapter.in.web.dto.ProductTeaserModuleDto;
import coding.challenge.homefeed.domain.Product;
import coding.challenge.homefeed.domain.ProductTeaserModule;

@Component
public class ProductTeaserModuleDtoMapper implements HomefeedModuleDtoMapper<ProductTeaserModule> {

     @Override
    public Class<ProductTeaserModule> supports() {
        return ProductTeaserModule.class;
    }

    @Override
    public ProductTeaserModuleDto toDto(ProductTeaserModule module) {
        return new ProductTeaserModuleDto(
                module.title,
                module.products.stream()
                        .map(this::toProductCardDto)
                        .toList()
        );
    }

    private ProductCardDto toProductCardDto(Product product) {
        var dto = new ProductCardDto();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setImageUrl(product.getImageUrl());
        return dto;
    }
    
}
