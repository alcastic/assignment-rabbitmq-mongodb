import com.google.common.io.Files;
import org.apache.qpid.server.Broker;
import org.apache.qpid.server.BrokerOptions;

public class EmbeddedQpidBroker {
    private static final int BROKER_PORT = 5672;
    private static final String QPID_CONFIG_JSON_JSON = "/qpid-config.json.json";
    public static final String EMBEDDED_BROKER = "embedded-broker";


    protected final Broker broker = new Broker();
    protected final BrokerOptions brokerOptions = new BrokerOptions();

    public EmbeddedQpidBroker() {
        brokerOptions.setConfigProperty("broker.name", EMBEDDED_BROKER);
        brokerOptions.setConfigProperty("qpid.amqp_port", String.valueOf(BROKER_PORT));
        brokerOptions.setConfigProperty("qpid.work_dir", Files.createTempDir().getAbsolutePath());
        brokerOptions.setInitialConfigurationLocation(getClass().getResource(QPID_CONFIG_JSON_JSON).toString());
    }

    public void start() throws Exception {
        broker.startup(brokerOptions);
    }

    public void stop() {
        broker.shutdown();
    }
}