package uk.jamierocks.classicapi.event.server;

import uk.jamierocks.classicapi.event.Event;
import uk.jamierocks.classicapi.network.status.ServerIdentificationResponse;

/**
 * The server identification event.
 */
public class ServerIdentificationEvent implements Event {

    private ServerIdentificationResponse response;

    public ServerIdentificationEvent(ServerIdentificationResponse response) {
        this.response = response;
    }

    public ServerIdentificationResponse getResponse() {
        return this.response;
    }

    public void setResponse(ServerIdentificationResponse response) {
        this.response = response;
    }
}
