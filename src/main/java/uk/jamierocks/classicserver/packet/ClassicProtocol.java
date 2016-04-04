package uk.jamierocks.classicserver.packet;

import org.spacehq.packetlib.Client;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.crypt.PacketEncryption;
import org.spacehq.packetlib.packet.DefaultPacketHeader;
import org.spacehq.packetlib.packet.PacketHeader;
import org.spacehq.packetlib.packet.PacketProtocol;
import uk.jamierocks.classicserver.packet.client.ClientChatPacket;
import uk.jamierocks.classicserver.packet.client.ClientIdentificationPacket;
import uk.jamierocks.classicserver.packet.server.ServerLevelInitialisePacket;
import uk.jamierocks.classicserver.packet.server.ServerPingPacket;
import uk.jamierocks.classicserver.packet.server.ServerIdentificationPacket;
import uk.jamierocks.classicserver.packet.server.ServerChatPacket;
import uk.jamierocks.classicserver.packet.server.ServerUpdateUserTypePacket;

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
        this.registerIncoming(0, ClientIdentificationPacket.class);
        this.registerIncoming(13, ClientChatPacket.class);

        this.registerOutgoing(0, ServerIdentificationPacket.class);
        this.registerOutgoing(1, ServerPingPacket.class);
        this.registerOutgoing(2, ServerLevelInitialisePacket.class);
        this.registerOutgoing(13, ServerChatPacket.class);
        this.registerOutgoing(15, ServerUpdateUserTypePacket.class);
    }
}
