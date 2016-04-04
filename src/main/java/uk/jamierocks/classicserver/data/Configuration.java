package uk.jamierocks.classicserver.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

/**
 * The server's configuration.
 */
public class Configuration {

    public static final File CONFIG_FILE = new File("server.properties");

    private String name;
    private String motd;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotd() {
        return this.motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }

    public boolean save() {
        Properties properties = new Properties();
        properties.setProperty("name", this.name);
        properties.setProperty("motd", this.motd);

        try {
            properties.store(new FileOutputStream(CONFIG_FILE), "ClassicServer properties");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Optional<Configuration> load() {
        if (!CONFIG_FILE.exists() || !CONFIG_FILE.isFile()) {
            return Optional.empty();
        }

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(CONFIG_FILE));

            Configuration config = new Configuration();
            config.setName(properties.getProperty("name"));
            config.setMotd(properties.getProperty("motd"));

            return Optional.of(config);
        } catch (IOException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
}
