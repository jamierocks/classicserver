package uk.jamierocks.classicserver;

import com.flowpowered.math.vector.Vector3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.event.server.ServerAdapter;
import org.spacehq.packetlib.event.server.SessionAddedEvent;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;
import org.spacehq.packetlib.tcp.TcpSessionFactory;
import uk.jamierocks.classicserver.data.Configuration;
import uk.jamierocks.classicserver.data.Operators;
import uk.jamierocks.classicserver.packet.ClassicProtocol;
import uk.jamierocks.classicserver.packet.client.ClientIdentificationPacket;
import uk.jamierocks.classicserver.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The classicserver.
 */
public class ClassicServer implements uk.jamierocks.classicapi.Server {

    public static Logger LOGGER = LoggerFactory.getLogger("ClassicServer");

    private Configuration config;
    private List<String> operators;
    private List<Player> players = new ArrayList<>();
    private AtomicInteger atomicInt = new AtomicInteger();

    public ClassicServer() {
        Optional<Configuration> configuration = Configuration.load();
        if (configuration.isPresent()) {
            this.config = configuration.get();
        }

        this.operators = Operators.getOperators();

        Server server = new Server("127.0.0.1", 25565, ClassicProtocol.class, new TcpSessionFactory());

        server.addListener(new ServerAdapter() {
            @Override
            public void sessionAdded(SessionAddedEvent event) {
                event.getSession().addListener(new SessionAdapter() {
                    @Override
                    public void packetReceived(PacketReceivedEvent event) {
                        if (event.getPacket() instanceof ClientIdentificationPacket) {
                            ClientIdentificationPacket identificationPacket = event.getPacket();
                            players.add(new Player(atomicInt.getAndIncrement(),
                                    identificationPacket.getUsername(),
                                    new Vector3d(0, 0, 0),
                                    event.getSession()));
                        }
                    }
                });
            }
        });
    }

    @Override
    public String getName() {
        return this.config.getName();
    }

    @Override
    public int getPort() {
        return this.config.getPort();
    }

    @Override
    public int getPlayerCount() {
        return this.players.size();
    }

    @Override
    public int getMaxPlayers() {
        return this.config.getMaxPlayers();
    }

    @Override
    public boolean isPublic() {
        return this.config.isPublic();
    }

    @Override
    public int getProtocolVersion() {
        return MinecraftConstants.PROTOCOL_VERSION;
    }
}