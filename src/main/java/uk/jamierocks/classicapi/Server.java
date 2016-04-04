package uk.jamierocks.classicapi;

/**
 * Represents a Minecraft Classic server.
 */
public interface Server {

    String getName();

    int getPort();

    int getPlayerCount();

    int getMaxPlayers();

    boolean isPublic();

    int getProtocolVersion();
}
