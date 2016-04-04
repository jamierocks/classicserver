package uk.jamierocks.classicserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.jamierocks.classicserver.data.Configuration;
import uk.jamierocks.classicserver.data.Operators;

import java.util.List;
import java.util.Optional;

/**
 * The classicserver.
 */
public class ClassicServer {

    public static Logger LOGGER = LoggerFactory.getLogger("ClassicServer");

    private Configuration config;
    private List<String> operators;

    public ClassicServer() {
        Optional<Configuration> configuration = Configuration.load();
        if (configuration.isPresent()) {
            this.config = configuration.get();
        }

        this.operators = Operators.getOperators();
    }

}
