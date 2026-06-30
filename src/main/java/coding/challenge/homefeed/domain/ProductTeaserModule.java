package coding.challenge.homefeed.domain;

import java.util.List;

public class ProductTeaserModule implements HomefeedModule {
    public String title;
    public List<Product> products;

    public ProductTeaserModule(String title, List<Product> products) {
        this.title = title;
        this.products = products;
    }
}
