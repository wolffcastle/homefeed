package coding.challenge.homefeed.adapter.in.web.dto;

/**
 * 
 * SaleBannerModuleDto
 * Delivers concrete JSON structure for the sale banner module in the homefeed response.
 * @param type
 * @param headline
 * @param subline
 * @param imageUrl
 * @param ctaLabel
 * @param targetUrl
 */
public record SaleBannerModuleDto(       
        String type,
        String headline,
        String subline,
        String imageUrl,
        String ctaLabel,
        String targetUrl
) implements HomefeedModuleDto {

    public SaleBannerModuleDto(String headline, String subline, String imageUrl, String ctaLabel, String targetUrl) {
        this("sale_banner", headline, subline, imageUrl, ctaLabel, targetUrl);
    }
}
