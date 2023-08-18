package com.dpoint.tutorial.dpointtestproducer;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.HttpTestTarget;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.SpringApplication;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@Provider("test_provider")
@PactFolder("src/test/resources/pacts")
public class PactProviderLiveTest {

    private static ConfigurableWebApplicationContext application;

    @BeforeAll
    public static void start() {
        application = (ConfigurableWebApplicationContext) SpringApplication.run(DpointtestproducerApplication.class);
    }

    @BeforeEach
    void before(PactVerificationContext context) {
        // Fetch server host and port from your application's configuration
        String serverHost = application.getEnvironment().getProperty("server.host");
        int serverPort = Integer.parseInt(application.getEnvironment().getProperty("server.port"));

        // Set up the HttpTestTarget
        context.setTarget(new HttpTestTarget("localhost", serverPort));
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }

    @State("test GET")
    public void toGetState() {
    }

    @State("test POST")
    public void toPostState() {
    }

    @State("test GET employees")
    public void toGetEmployeesState() {
    }
}
