package coding.challenge.homefeed.adapter.in.web.mapper;

import org.springframework.stereotype.Component;

import coding.challenge.homefeed.adapter.in.web.dto.HomefeedModuleDto;
import coding.challenge.homefeed.adapter.in.web.dto.SaleBannerModuleDto;
import coding.challenge.homefeed.domain.SaleBannerModule;

@Component
public class SaleBannerModuleDtoMapper implements HomefeedModuleDtoMapper<SaleBannerModule>{
    
    @Override
    public Class<SaleBannerModule> supports() {
        return SaleBannerModule.class;
    }

    @Override
    public HomefeedModuleDto toDto(SaleBannerModule module) {
        return new SaleBannerModuleDto(
            module.headline(),
            module.subline(),
            module.imageUrl(),
            module.ctaLabel(),
            module.targetUrl()
        );
    }
}
