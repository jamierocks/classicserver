package uk.jamierocks.classicserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spacehq.packetlib.Server;
import org.spacehq.packetlib.event.server.ServerAdapter;
import org.spacehq.packetlib.event.server.ServerClosingEvent;
import org.spacehq.packetlib.event.server.SessionAddedEvent;
import org.spacehq.packetlib.event.session.PacketReceivedEvent;
import org.spacehq.packetlib.event.session.SessionAdapter;
import org.spacehq.packetlib.tcp.TcpSessionFactory;
import uk.jamierocks.classicapi.event.server.ServerIdentificationEvent;
import uk.jamierocks.classicapi.network.status.ServerIdentificationResponse;
import uk.jamierocks.classicserver.data.Configuration;
import uk.jamierocks.classicserver.data.Operators;
import uk.jamierocks.classicserver.data.UserType;
import uk.jamierocks.classicserver.packet.ClassicProtocol;
import uk.jamierocks.classicserver.packet.client.ClientIdentificationPacket;
import uk.jamierocks.classicserver.packet.server.ServerIdentificationPacket;
import uk.jamierocks.classicserver.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The classicserver.
 */
public class ClassicServer extends ServerAdapter implements uk.jamierocks.classicapi.Server {

    public static Logger LOGGER = LoggerFactory.getLogger("ClassicServer");

    private Heartbeat heartbeat;
    private Configuration config;
    private List<String> operators;
    private List<Player> players = new ArrayList<>();
    private AtomicInteger atomicInt = new AtomicInteger();

    public ClassicServer() {
        Optional<Configuration> configuration = Configuration.load();
        if (configuration.isPresent()) {
            this.config = configuration.get();
        } else {
            this.config = new Configuration();
        }

        this.operators = Operators.getOperators();

        Server server = new Server("192.168.1.67", this.getPort(), ClassicProtocol.class, new TcpSessionFactory());
        server.addListener(this);
        server.bind();

        this.heartbeat = new Heartbeat(this);
        new Thread(this.heartbeat, "HeartbeatThread").start();
    }

    @Override
    public void sessionAdded(SessionAddedEvent event) {
        event.getSession().addListener(new SessionAdapter() {
            @Override
            public void packetReceived(PacketReceivedEvent event) {
                if (event.getPacket() instanceof ClientIdentificationPacket) {
                    ClientIdentificationPacket identificationPacket = event.getPacket();

                    Player player = new Player(atomicInt.getAndIncrement(),
                            identificationPacket.getUsername(),
                            event.getSession());
                    players.add(player);

                    UserType userType = operators.contains(player.getName()) ?
                            UserType.OPERATOR : UserType.NORMAL;

                    ServerIdentificationEvent identificationEvent = new ServerIdentificationEvent(
                            new ServerIdentificationResponse() {
                                @Override
                                public String getName() {
                                    return ClassicServer.this.getName();
                                }

                                @Override
                                public String getDescription() {
                                    return ClassicServer.this.getMOTD();
                                }

                                @Override
                                public int getProtocolVersion() {
                                    return ClassicServer.this.getProtocolVersion();
                                }

                                @Override
                                public UserType getUserType() {
                                    return userType;
                                }
                            });
                    // TODO: Fire event

                    event.getSession().send(new ServerIdentificationPacket(identificationEvent.getResponse()));

                    LOGGER.info(player.getName() + " has joined.");
                }
            }
        });
    }

    @Override
    public void serverClosing(ServerClosingEvent event) {
        this.heartbeat.stopBeating();
        this.heartbeat = null;
    }

    @Override
    public String getName() {
        return this.config.getName();
    }

    @Override
    public String getMOTD() {
        return this.config.getMotd();
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
