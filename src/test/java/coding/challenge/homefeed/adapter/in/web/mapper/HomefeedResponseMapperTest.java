package coding.challenge.homefeed.adapter.in.web.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;

import coding.challenge.homefeed.adapter.in.web.dto.ProductTeaserModuleDto;
import coding.challenge.homefeed.application.exceptions.UnsupportedHomefeedModuleException;
import coding.challenge.homefeed.domain.HomefeedModule;
import coding.challenge.homefeed.domain.Product;
import coding.challenge.homefeed.domain.ProductTeaserModule;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class HomefeedResponseMapperTest {

    @Test
    /**
     * verifies that the ProductTeaserModule is correctly mapped to the ProductTeaserModuleDto
     * and that the type field is correctly set to "product_teaser", 1 product and the product id is correctly mapped to the dto
     */
    void mapsProductTeaserModuleToApiDto() {
        HomefeedResponseMapper mapper = new HomefeedResponseMapper(List.of(
                new GreetingModuleDtoMapper(),
                new SaleBannerModuleDtoMapper(),
                new ProductTeaserModuleDtoMapper()
        ));
        ProductTeaserModule module = new ProductTeaserModule(
                "Empfohlen fuer dich",
                List.of(new Product("xyid-1001", "Relaxed Linen Shirt", new BigDecimal("49.90"), "image.jpg"))
        );

        ProductTeaserModuleDto result = (ProductTeaserModuleDto) mapper.toDto(module);

        assertThat(result.type()).isEqualTo("product_teaser");
        assertThat(result.getProducts()).hasSize(1);
        assertThat(result.getProducts().getFirst().getId()).isEqualTo("xyid-1001");
    }

    @Test
    /**
     * verifies that an UnsupportedHomefeedModuleException is thrown when a module type has no registered mapper
     */
    void failsFastWhenModuleTypeHasNoMapper() {
        HomefeedResponseMapper mapper = new HomefeedResponseMapper(List.of());
        HomefeedModule unsupportedModule = new HomefeedModule() {
        };

        assertThatThrownBy(() -> mapper.toDto(unsupportedModule))
                .isInstanceOf(UnsupportedHomefeedModuleException.class)
                .hasMessageContaining("No DTO mapper registered");
    }
}
