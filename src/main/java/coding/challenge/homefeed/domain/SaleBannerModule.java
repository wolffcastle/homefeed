package coding.challenge.homefeed.domain;

public record SaleBannerModule(
        String headline,
        String subline,
        String imageUrl,
        String ctaLabel,
        String targetUrl
) implements HomefeedModule {
}
