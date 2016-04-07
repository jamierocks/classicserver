package uk.jamierocks.classicserver.packet;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.PacketHeader;

import java.io.IOException;

/**
 * The Minecraft Classic implementation of {@link PacketHeader}.
 */
public class ClassicPacketHeader implements PacketHeader {

    @Override
    public boolean isLengthVariable() {
        return false;
    }

    @Override
    public int getLengthSize() {
        return 0;
    }

    @Override
    public int getLengthSize(int i) {
        return 0;
    }

    @Override
    public int readLength(NetInput netInput, int i) throws IOException {
        return 0;
    }

    @Override
    public void writeLength(NetOutput netOutput, int i) throws IOException {

    }

    @Override
    public int readPacketId(NetInput netInput) throws IOException {
        return netInput.readByte();
    }

    @Override
    public void writePacketId(NetOutput netOutput, int i) throws IOException {
        netOutput.writeByte(i);
    }
}
