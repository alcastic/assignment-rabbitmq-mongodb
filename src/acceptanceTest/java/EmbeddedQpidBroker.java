import com.google.common.io.Files;
import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;

/**
 * We shouldn't need external things for testing
 */
public class EmbeddedQpidBroker {
    private static final int BROKER_PORT = 5672;

    private final Broker broker = new Broker();
    private final BrokerOptions brokerOptions = new BrokerOptions();

    public EmbeddedQpidBroker() {
        final String configFileName = "/test-initial-config.json";

        // prepare options
        brokerOptions.setConfigProperty("broker.name", "embedded-broker");
        brokerOptions.setConfigProperty("qpid.amqp_port", String.valueOf(BROKER_PORT));
        brokerOptions.setConfigProperty("qpid.work_dir", Files.createTempDir().getAbsolutePath());
        brokerOptions.setInitialConfigurationLocation(getClass().getResource(configFileName).toString());
    }

    public void start() throws Exception {
        broker.startup(brokerOptions);
    }

    public void stop() {
        broker.shutdown();
    }
}