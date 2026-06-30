package coding.challenge.homefeed.application.exceptions;

import coding.challenge.homefeed.domain.HomefeedModule;

public class UnsupportedHomefeedModuleException extends RuntimeException {
    public UnsupportedHomefeedModuleException(HomefeedModule module) {
        super("No DTO mapper registered for module " + module.getClass().getSimpleName());
    }

}
