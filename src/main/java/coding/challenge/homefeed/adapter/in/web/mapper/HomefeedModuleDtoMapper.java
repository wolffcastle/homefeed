package coding.challenge.homefeed.adapter.in.web.mapper;

import coding.challenge.homefeed.adapter.in.web.dto.HomefeedModuleDto;
import coding.challenge.homefeed.domain.HomefeedModule;

/**
 * HomefeedModuleDtoMapper
 */
public interface HomefeedModuleDtoMapper<T extends HomefeedModule> {

    Class<T> supports();

    HomefeedModuleDto toDto(T module);
}
