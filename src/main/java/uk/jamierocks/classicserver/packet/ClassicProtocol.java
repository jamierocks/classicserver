package uk.jamierocks.classicserver.packet;

import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.crypt.PacketEncryption;
import org.spacehq.packetlib.packet.DefaultPacketHeader;
import org.spacehq.packetlib.packet.PacketHeader;
import org.spacehq.packetlib.packet.PacketProtocol;
import uk.jamierocks.classicserver.packet.client.ClientMessagePacket;
import uk.jamierocks.classicserver.packet.server.LevelInitialisePacket;
import uk.jamierocks.classicserver.packet.server.PingPacket;
import uk.jamierocks.classicserver.packet.server.ServerIdentificationPacket;
import uk.jamierocks.classicserver.packet.server.ServerMessagePacket;
import uk.jamierocks.classicserver.packet.server.UpdateUserTypePacket;

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
        this.registerIncoming(13, ClientMessagePacket.class);

        this.registerOutgoing(0, ServerIdentificationPacket.class);
        this.registerOutgoing(1, PingPacket.class);
        this.registerOutgoing(2, LevelInitialisePacket.class);
        this.registerOutgoing(13, ServerMessagePacket.class);
        this.registerOutgoing(15, UpdateUserTypePacket.class);
    }
}
