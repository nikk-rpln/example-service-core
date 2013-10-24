package no.schibsted.example.test;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mule.api.MuleMessage;
import org.mule.module.client.MuleClient;
import org.mule.tck.junit4.FunctionalTestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationTest extends FunctionalTestCase {

    private static final Logger logger = LoggerFactory
            .getLogger(IntegrationTest.class);
    private MuleClient client;

    private void loadPropertiesFile(final String fileName) throws Exception {
        final InputStream inStream = getClass().getClassLoader().getResourceAsStream(fileName);
        if (inStream != null) {
            logger.info("Loading " + fileName);
            final Properties properties = System.getProperties();
            properties.load(inStream);
            System.setProperties(properties);
        }
    }

    protected void setProperties() {
        try {
            loadPropertiesFile("example-service-core.properties");
            loadPropertiesFile("example-service-core-override.properties");
            loadPropertiesFile("environment.properties");
        } catch (Exception e) {
            logger.error("Test failed while loading properties", e);
        }
    }

    @Override
    protected String getConfigResources() {
        // Must call set properties from here since immediately after config
        // resources have been detected Mule will be started.
        setProperties();
        return "example-service-endpoint.xml";
    }

    @Before
    public void before() throws Exception {
        logger.info("Running @Before target");
        client = new MuleClient(muleContext);
    }

    @Test
    public void testSendVM() throws Exception {
        logger.info("Running testSendVM");
        String payload = "foo";
        Map<String, Object> properties = new HashMap<String, Object>();
        MuleMessage result = client.send("vm://service/asynch", payload,
                properties);
        logger.trace(result.getPayloadAsString());
        Assert.assertTrue(true);
    }

    //@Test
    public void testSendHTTP() throws Exception {
        logger.info("Running testSendHTTP");
        String payload = "foo";
        Map<String, Object> properties = new HashMap<String, Object>();
        MuleMessage result = client.send("http://localhost:8081", payload,
                properties);
        logger.trace(result.getPayloadAsString());
        Assert.assertTrue(true);
    }

}
