package coding.challenge.homefeed.application.port.in;

import java.util.List;

import coding.challenge.homefeed.domain.HomefeedModule;

/**
 * Use case for retrieving the homefeed for a user.
 * Describes what the application should do, but not how it should be done.
 */
public interface GetHomefeedUseCase {
    List<HomefeedModule> getHomefeed(String userId);
}
