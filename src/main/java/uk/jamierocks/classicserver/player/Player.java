package uk.jamierocks.classicserver.player;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.nbt.CompoundTag;
import org.spacehq.packetlib.Session;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;
import uk.jamierocks.classicserver.packet.client.ClientChatPacket;
import uk.jamierocks.classicserver.packet.server.ServerChatPacket;
import uk.jamierocks.classicserver.util.NBTUtil;

/**
 * Represents a player.
 */
public class Player extends SessionAdapter {

    private int id;
    private String name;
    private Vector3d pos;
    private Session session;
    private CompoundTag compoundTag;

    public Player(int id, String name, Session session) {
        this.id = id;
        this.name = name;
        this.session = session;
        this.session.addListener(this);

        this.compoundTag = NBTUtil.readOrCreatePlayerData(this.name);
        if (this.compoundTag.getValue().containsKey("x") &&
                this.compoundTag.getValue().containsKey("y") &&
                this.compoundTag.getValue().containsKey("z")) {
            this.pos = new Vector3d(
                    (Integer) this.compoundTag.getValue().get("x").getValue(),
                    (Integer) this.compoundTag.getValue().get("y").getValue(),
                    (Integer) this.compoundTag.getValue().get("z").getValue());
        } else {
            this.pos = new Vector3d(0, 0, 0);
        }
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
