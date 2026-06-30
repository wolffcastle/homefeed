package coding.challenge.homefeed.adapter.in.web;

import java.util.List;

import coding.challenge.homefeed.adapter.in.web.dto.HomefeedModuleDto;
import coding.challenge.homefeed.adapter.in.web.mapper.HomefeedResponseMapper;
import coding.challenge.homefeed.application.port.in.GetHomefeedUseCase;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * HomefeedController
 * @author Philipp Wolff
 * HomefeedController is a REST controller that handles HTTP requests related to the homefeed functionality. It provides an endpoint to retrieve the homefeed for a specific user.
 */
@RestController
public class HomefeedController {
    
    /*
     * Controller for handling homefeed-related requests. 
    * It uses the GetHomefeedUseCase to retrieve the homefeed data and the HomefeedResponseMapper to convert domain objects into DTOs for the response.
     */
    private final GetHomefeedUseCase getHomefeedUseCase;
    private final HomefeedResponseMapper responseMapper;


    /**
     * @param getHomefeedUseCase
     * @param responseMapper
     */
    public HomefeedController(GetHomefeedUseCase getHomefeedUseCase, HomefeedResponseMapper responseMapper) {
        this.getHomefeedUseCase = getHomefeedUseCase;
        this.responseMapper = responseMapper;
    }

    /**
     * GetHomefeedUseCase is an interface that defines the use case for retrieving the homefeed for a specific user.
     *  It encapsulates the business logic related to fetching the homefeed data.
     * @param userId
     * @return a sorted list of HomefeedModuleDto objects representing the homefeed for the specified user.
     */
    @GetMapping(value = "/api/homefeed", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<HomefeedModuleDto> getHomefeed(@RequestParam(defaultValue = "felix") String userId) {
        return getHomefeedUseCase.getHomefeed(userId).stream()
                .map(responseMapper::toDto)
                .toList();
    }

}
