package uk.jamierocks.classicserver.packet.server;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

/**
 * The 'ping' packet.
 */
public class PingPacket implements Packet {

    public PingPacket() {
    }

    @Override
    public void read(NetInput netInput) throws IOException {

    }

    @Override
    public void write(NetOutput netOutput) throws IOException {

    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
