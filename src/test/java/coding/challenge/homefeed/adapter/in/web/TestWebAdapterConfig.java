package coding.challenge.homefeed.adapter.in.web;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

import coding.challenge.homefeed.adapter.in.web.error.ApiExceptionHandler;
import coding.challenge.homefeed.adapter.in.web.mapper.GreetingModuleDtoMapper;
import coding.challenge.homefeed.adapter.in.web.mapper.HomefeedResponseMapper;
import coding.challenge.homefeed.adapter.in.web.mapper.ProductTeaserModuleDtoMapper;
import coding.challenge.homefeed.adapter.in.web.mapper.SaleBannerModuleDtoMapper;

@TestConfiguration
@Import({
        HomefeedResponseMapper.class,
        GreetingModuleDtoMapper.class,
        SaleBannerModuleDtoMapper.class,
        ProductTeaserModuleDtoMapper.class,
        ApiExceptionHandler.class
})
public class TestWebAdapterConfig {
    
}
