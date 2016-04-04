package uk.jamierocks.classicserver.packet.server;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;

import java.io.IOException;

/**
 * The level initialise packet.
 */
public class LevelInitialisePacket implements Packet {

    public LevelInitialisePacket() {
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
