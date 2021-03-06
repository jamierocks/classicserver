package uk.jamierocks.classicserver.packet.server;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;
import uk.jamierocks.classicserver.data.UserType;

import java.io.IOException;

/**
 * The packet for updating the user type.
 */
public class ServerUpdateUserTypePacket implements Packet {

    private UserType userType;

    private ServerUpdateUserTypePacket() {
    }

    public ServerUpdateUserTypePacket(UserType userType) {
        this.userType = userType;
    }

    public UserType getUserType() {
        return this.userType;
    }

    @Override
    public void read(NetInput netInput) throws IOException {
        this.userType = UserType.fromId(netInput.readByte());
    }

    @Override
    public void write(NetOutput netOutput) throws IOException {
        netOutput.writeByte(this.userType.getId());
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
