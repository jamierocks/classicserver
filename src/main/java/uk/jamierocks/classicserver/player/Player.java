package uk.jamierocks.classicserver.player;

import com.flowpowered.math.vector.Vector3d;
import org.spacehq.packetlib.Session;

/**
 * Represents a player.
 */
public class Player {

    private int id;
    private String name;
    private Vector3d pos;
    private Session session;

    public Player(int id, String name, Vector3d pos, Session session) {
        this.id = id;
        this.name = name;
        this.pos = pos;
        this.session = session;
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
}
