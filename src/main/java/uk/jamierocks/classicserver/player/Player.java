package uk.jamierocks.classicserver.player;

import com.flowpowered.math.vector.Vector3d;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;
import uk.jamierocks.classicserver.packet.client.ClientChatPacket;
import uk.jamierocks.classicserver.packet.server.ServerChatPacket;

/**
 * Represents a player.
 */
public class Player extends SessionAdapter {

    private int id;
    private String name;
    private Vector3d pos;
    private Session session;

    public Player(int id, String name, Vector3d pos, Session session) {
        this.id = id;
        this.name = name;
        this.pos = pos;
        this.session = session;
        this.session.addListener(this);
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Vector3d getPos() {
        return this.pos;
    }

    public Session getSession() {
        return this.session;
    }

    @Override
    public void packetReceived(PacketReceivedEvent event) {
        if (event.getPacket() instanceof ClientChatPacket) {
            ClientChatPacket chatPacket = event.getPacket();

            event.getSession().send(
                    new ServerChatPacket(this.getId(),
                            this.getName() + ": " + chatPacket.getMessage()));
        }
    }
}
