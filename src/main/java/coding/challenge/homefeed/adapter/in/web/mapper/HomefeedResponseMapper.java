package coding.challenge.homefeed.adapter.in.web.mapper;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import coding.challenge.homefeed.adapter.in.web.dto.HomefeedModuleDto;
import coding.challenge.homefeed.application.exceptions.UnsupportedHomefeedModuleException;
import coding.challenge.homefeed.domain.HomefeedModule;

/**
 * 
 * HomefeedResponseMapper needs to be a component so that Spring can inject the list of HomefeedModuleDtoMapper beans into its constructor. 
 * This allows the mapper to dynamically map different types of HomefeedModule to their corresponding DTOs.
 * Decides during runtime which mapper to use based on the type of HomefeedModule. 
 * If no mapper is found for a given module type, it throws an UnsupportedHomefeedModuleException.
 * For a new module you need to create apart of a new module also XYModuleDto and XYModuleDtoMapper and declare it as a Spring bean (e.g., with @Component) so that it can be injected into HomefeedResponseMapper.
 */
@Component
public class HomefeedResponseMapper {

    private final Map<Class<? extends HomefeedModule>, HomefeedModuleDtoMapper<? extends HomefeedModule>> mappers;

    public HomefeedResponseMapper(List<HomefeedModuleDtoMapper<? extends HomefeedModule>> mappers) {
        this.mappers = mappers.stream()
                .collect(Collectors.toUnmodifiableMap(HomefeedModuleDtoMapper::supports, Function.identity()));
    }

    public HomefeedModuleDto toDto(HomefeedModule module) {
        HomefeedModuleDtoMapper<? extends HomefeedModule> mapper = mappers.get(module.getClass());
        if (mapper == null) {
            throw new UnsupportedHomefeedModuleException(module);
        }
        return mapWithType(mapper, module);
    }

    @SuppressWarnings("unchecked")
    private <T extends HomefeedModule> HomefeedModuleDto mapWithType(
            HomefeedModuleDtoMapper<? extends HomefeedModule> mapper,
            HomefeedModule module
    ) {
        return ((HomefeedModuleDtoMapper<T>) mapper).toDto((T) module);
    }
}
