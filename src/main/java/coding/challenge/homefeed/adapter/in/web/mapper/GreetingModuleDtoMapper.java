package coding.challenge.homefeed.adapter.in.web.mapper;

import org.springframework.stereotype.Component;

import coding.challenge.homefeed.adapter.in.web.dto.GreetingModuleDto;
import coding.challenge.homefeed.domain.GreetingModule;

@Component
public class GreetingModuleDtoMapper implements HomefeedModuleDtoMapper<GreetingModule>{
    @Override
    public Class<GreetingModule> supports() {
        return GreetingModule.class;
    }

    @Override
    public GreetingModuleDto toDto(GreetingModule module) {
        return new GreetingModuleDto(module.title(), module.subtitle());
    }
    
}
