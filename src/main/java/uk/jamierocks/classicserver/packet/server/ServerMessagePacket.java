package uk.jamierocks.classicserver.packet.server;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

/**
 * The server message packet.
 */
public class ServerMessagePacket implements Packet {

    private int playerId;
    private String message;

    private ServerMessagePacket() {
    }

    public ServerMessagePacket(int playerId, String message) {
        this.playerId = playerId;
        this.message = message;
    }

    public int getPlayerId() {
        return this.playerId;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public void read(NetInput netInput) throws IOException {
        this.playerId = netInput.readInt();
        this.message = netInput.readString();
    }

    @Override
    public void write(NetOutput netOutput) throws IOException {
        netOutput.writeInt(this.playerId);
        netOutput.writeString(this.message);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
