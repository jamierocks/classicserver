package uk.jamierocks.classicserver.packet.client;

import org.spacehq.packetlib.io.NetInput;
import org.spacehq.packetlib.io.NetOutput;
import org.spacehq.packetlib.packet.Packet;
import uk.jamierocks.classicserver.MinecraftConstants;

import java.io.IOException;

/**
 * The client identification packet.
 */
public class ClientIdentificationPacket implements Packet {

    private int protocolVersion;
    private String username;
    private String verificationKey;
    private int unused;

    private ClientIdentificationPacket() {
    }

    public ClientIdentificationPacket(String username, String verificationKey) {
        this(MinecraftConstants.PROTOCOL_VERSION, username, verificationKey);
    }

    public ClientIdentificationPacket(int protocolVersion, String username, String verificationKey) {
        this.protocolVersion = protocolVersion;
        this.username = username;
        this.verificationKey = verificationKey;
        this.unused = 0;
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public String getUsername() {
        return this.username;
    }

    public String getVerificationKey() {
        return this.verificationKey;
    }

    @Override
    public void read(NetInput netInput) throws IOException {
        this.protocolVersion = netInput.readByte();
        this.username = netInput.readString();
        this.verificationKey = netInput.readString();
        this.unused = netInput.readByte();
    }

    @Override
    public void write(NetOutput netOutput) throws IOException {
        netOutput.writeByte(this.protocolVersion);
        netOutput.writeString(this.username);
        netOutput.writeString(this.verificationKey);
        netOutput.writeByte(this.unused);
    }

    @Override
    public boolean isPriority() {
        return false;
    }
}
