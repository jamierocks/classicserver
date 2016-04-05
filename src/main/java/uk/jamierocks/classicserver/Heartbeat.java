package uk.jamierocks.classicserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.stream.Collectors;

/**
 * The Minecraft Classic heartbeat.
 */
public class Heartbeat implements Runnable {

    private boolean beating = true;
    private final ClassicServer server;

    public Heartbeat(ClassicServer server) {
        this.server = server;
    }

    public void stopBeating() {
        this.beating = false;
    }

    @Override
    public void run() {
        while (this.beating) {
            StringBuilder urlBuilder = new StringBuilder(MinecraftConstants.HEARTBEAT_URL + "?");
            try {
                urlBuilder.append("name=").append(URLEncoder.encode(this.server.getName(), "utf-8")).append("&");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            urlBuilder.append("port=").append(this.server.getPort()).append("&");
            urlBuilder.append("max=").append(this.server.getMaxPlayers()).append("&");
            urlBuilder.append("public=").append(this.server.isPublic()).append("&");
            urlBuilder.append("version=").append(this.server.getProtocolVersion()).append("&");
            urlBuilder.append("salt=").append("thisisarandomsalt").append("&");
            urlBuilder.append("users=").append(this.server.getPlayerCount()).append("&");
            try {
                urlBuilder.append("software=" + URLEncoder.encode("jamierocks' clasicserver", "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(urlBuilder.toString())
                    .openStream()))) {
                ClassicServer.LOGGER.info(urlBuilder.toString());
                ClassicServer.LOGGER.info("HEARTBEAT: " + reader.lines().collect(Collectors.toList()));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(45000);
            } catch(InterruptedException e) {
                break;
            }
        }
    }
}
