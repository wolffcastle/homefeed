package coding.challenge.homefeed.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import coding.challenge.homefeed.application.exceptions.InvalidHomefeedRequestException;
import coding.challenge.homefeed.application.port.in.GetHomefeedUseCase;
import coding.challenge.homefeed.application.port.out.LoadHomefeedModulesPort;
import coding.challenge.homefeed.domain.HomefeedModule;

@Service
public class GetHomefeedService implements GetHomefeedUseCase {
    private final LoadHomefeedModulesPort loadHomefeedModulesPort;

    public GetHomefeedService(LoadHomefeedModulesPort loadHomefeedModulesPort) {
        this.loadHomefeedModulesPort = loadHomefeedModulesPort;
    }

    
    public List<HomefeedModule> getHomefeed(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new InvalidHomefeedRequestException("userId must not be blank");
        }

        return loadHomefeedModulesPort.loadHomefeedForUser(userId.trim());
    }
    
}
