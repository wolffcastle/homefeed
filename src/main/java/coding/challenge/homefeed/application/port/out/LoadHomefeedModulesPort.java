package coding.challenge.homefeed.application.port.out;

import java.util.List;

import coding.challenge.homefeed.domain.HomefeedModule;

/**
 * Port for loading homefeed modules for a user.
 * Describes what the applications needs from the outside world, but not how it should be done.
 */
public interface LoadHomefeedModulesPort {
    List<HomefeedModule> loadHomefeedForUser(String userId);
}
