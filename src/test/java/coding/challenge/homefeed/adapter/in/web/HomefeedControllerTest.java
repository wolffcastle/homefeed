package coding.challenge.homefeed.adapter.in.web;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import coding.challenge.homefeed.application.port.in.GetHomefeedUseCase;
import coding.challenge.homefeed.domain.GreetingModule;
import coding.challenge.homefeed.domain.Product;
import coding.challenge.homefeed.domain.ProductTeaserModule;
import coding.challenge.homefeed.domain.SaleBannerModule;

@WebMvcTest(controllers = HomefeedController.class)
@Import(TestWebAdapterConfig.class)
public class HomefeedControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private GetHomefeedUseCase getHomefeedUseCase;

    @Test
    /**
     * The test validates:
     * All three modules are returned in order
     * Each module has the correct type field
     * The greeting module contains the title
     * The sale banner has the CTA label
     * The product teaser has products with id populated
     * @throws Exception InvalidHomefeedRequestException/UnsupportedHomefeedModuleException
     */
    void returnsOrderedHomefeedModulesWithTypeField() throws Exception {
        when(getHomefeedUseCase.getHomefeed("felix")).thenReturn(List.of(
                new GreetingModule("Guten Morgen, felix", "Schoen, dass du wieder da bist."),
                new SaleBannerModule(
                        "Mid Season Sale",
                        "Bis zu 30% auf ausgewaehlte Styles",
                        "https://cdn.example.com/campaigns/mid-season-sale.jpg",
                        "Jetzt shoppen",
                        "app://campaigns/mid-season-sale"
                ),
                new ProductTeaserModule(
                        "Empfohlen fuer dich",
                        List.of(new Product("xyid-1001", "Relaxed Linen Shirt", new BigDecimal("49.90"), "https://cdn.example.com/products/xyid-1001.jpg"))
                )
        ));

        mockMvc.perform(get("/api/homefeed").param("userId", "felix"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].type").value("greeting"))
                .andExpect(jsonPath("$[0].title").value("Guten Morgen, felix"))
                .andExpect(jsonPath("$[1].type").value("sale_banner"))
                .andExpect(jsonPath("$[1].ctaLabel").value("Jetzt shoppen"))
                .andExpect(jsonPath("$[2].type").value("product_teaser"))
                .andExpect(jsonPath("$[2].products[0].id").value("xyid-1001"));

        verify(getHomefeedUseCase).getHomefeed("felix");
    }
}
