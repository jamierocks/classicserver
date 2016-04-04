package uk.jamierocks.classicserver.packet.client;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

/**
 * The message packet.
 */
public class ClientMessagePacket implements Packet {

    private int unused; // This is always 0xFF (255)
    private String message;

    private ClientMessagePacket() {
    }

    public ClientMessagePacket(String message) {
        this.unused = 255;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public void read(NetInput netInput) throws IOException {
        this.unused = netInput.readInt();
        this.message = netInput.readString();
    }

    @Override
    public void write(NetOutput netOutput) throws IOException {
        netOutput.writeInt(this.unused);
        netOutput.writeString(this.message);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
