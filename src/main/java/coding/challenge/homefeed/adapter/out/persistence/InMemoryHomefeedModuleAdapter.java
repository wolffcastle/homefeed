package coding.challenge.homefeed.adapter.out.persistence;

import java.time.Clock;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Component;

import coding.challenge.homefeed.application.port.out.LoadHomefeedModulesPort;
import coding.challenge.homefeed.application.port.out.LoadRecommendedProductsPort;
import coding.challenge.homefeed.domain.GreetingModule;
import coding.challenge.homefeed.domain.HomefeedModule;
import coding.challenge.homefeed.domain.ProductTeaserModule;
import coding.challenge.homefeed.domain.SaleBannerModule;

@Component
public class InMemoryHomefeedModuleAdapter implements LoadHomefeedModulesPort {

    private final LoadRecommendedProductsPort loadRecommendedProductsPort;
    private final Clock clock;

    InMemoryHomefeedModuleAdapter(LoadRecommendedProductsPort loadRecommendedProductsPort, Clock clock) {
        this.loadRecommendedProductsPort = loadRecommendedProductsPort;
        this.clock = clock;
    }

    @Override
    public List<HomefeedModule> loadHomefeedForUser(String userId) {
        return List.of(
                new GreetingModule(greetingFor(userId), "Schoen, dass du wieder da bist."),
                new SaleBannerModule(
                        "Mid Season Sale",
                        "Bis zu 30% auf ausgewaehlte Styles",
                        "https://cdn.example.com/campaigns/mid-season-sale.jpg",
                        "Jetzt shoppen",
                        "app://campaigns/mid-season-sale"
                ),
                new ProductTeaserModule("Empfohlen fuer dich", loadRecommendedProductsPort.loadRecommendedProducts(userId, 4))
        );
    }

    private String greetingFor(String userId) {
        String name = capitalize(userId);
        int hour = LocalTime.now(clock).getHour();

        if (hour < 11) {
            return "Guten Morgen, " + name;
        }
        if (hour < 18) {
            return "Hallo, " + name;
        }
        return "Guten Abend, " + name;
    }

    private String capitalize(String value) {
        if (value.isBlank()) {
            return "Gast";
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }
}
