package uk.jamierocks.classicapi.network.status;

import uk.jamierocks.classicserver.data.UserType;

/**
 * Immutable interface to represent the server's identification response.
 */
public interface ServerIdentificationResponse {

    /**
     * Gets the name of the server.
     *
     * @return The name of the server.
     */
    String getName();

    /**
     * Gets the description (motd).
     *
     * @return The description.
     */
    String getDescription();

    /**
     * Gets the protocol version.
     *
     * @return The protocol version.
     */
    int getProtocolVersion();

    /**
     * Gets the user type of the connecting player.
     *
     * @return The user type of the connecting player.
     */
    UserType getUserType();
}
