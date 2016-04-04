package uk.jamierocks.classicserver.packet.server;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;
import uk.jamierocks.classicserver.data.UserType;

import java.io.IOException;

/**
 * The server identification packet.
 */
public class ServerIdentificationPacket implements Packet {

    private int protocolVersion;
    private String serverName;
    private String serverMOTD;
    private UserType userType;

    private ServerIdentificationPacket() {
    }

    public ServerIdentificationPacket(int protocolVersion, String serverName, String serverMOTD, UserType userType) {
        this.protocolVersion = protocolVersion;
        this.serverName = serverName;
        this.serverMOTD = serverMOTD;
        this.userType = userType;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public String getServerName() {
        return this.serverName;
    }

    public String getServerMOTD() {
        return this.serverMOTD;
    }

    public UserType getUserType() {
        return this.userType;
    }

    @Override
    public void read(NetInput netInput) throws IOException {
        this.protocolVersion = netInput.readInt();
        this.serverName = netInput.readString();
        this.serverMOTD = netInput.readString();
        this.userType = UserType.fromId(netInput.readInt());
    }

    @Override
    public void write(NetOutput netOutput) throws IOException {
        netOutput.writeInt(this.protocolVersion);
        netOutput.writeString(this.serverName);
        netOutput.writeString(this.serverMOTD);
        netOutput.writeInt(this.userType.getId());
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
