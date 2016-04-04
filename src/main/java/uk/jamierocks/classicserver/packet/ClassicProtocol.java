package uk.jamierocks.classicserver.packet;

import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.crypt.PacketEncryption;
import org.spacehq.packetlib.packet.DefaultPacketHeader;
import org.spacehq.packetlib.packet.PacketHeader;
import org.spacehq.packetlib.packet.PacketProtocol;
import uk.jamierocks.classicserver.packet.server.PingPacket;
import uk.jamierocks.classicserver.packet.server.ServerIdentificationPacket;

/**
 * The Minecraft Classic protocol.
 */
public class ClassicProtocol extends PacketProtocol {

    private PacketHeader header = new DefaultPacketHeader();

    public ClassicProtocol() {
    }

    @Override
    public String getSRVRecordPrefix() {
        return "_minecraft";
    }

    @Override
    public PacketHeader getPacketHeader() {
        return this.header;
    }

    @Override
    public PacketEncryption getEncryption() {
        return null;
    }

    @Override
    public void newClientSession(Client client, Session session) {

    }

    @Override
    public void newServerSession(Server server, Session session) {
        this.registerOutgoing(0, ServerIdentificationPacket.class);
        this.registerOutgoing(1, PingPacket.class);
    }
}
