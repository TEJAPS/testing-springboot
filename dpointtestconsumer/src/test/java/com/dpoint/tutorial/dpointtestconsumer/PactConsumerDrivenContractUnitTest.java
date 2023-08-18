package com.dpoint.tutorial.dpointtestconsumer;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.dsl.PactDslJsonArray;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.model.RequestResponsePact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "test_provider", hostInterface = "localhost")
public class PactConsumerDrivenContractUnitTest {

    @Pact(provider = "test_provider", consumer = "test_consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET")
                .uponReceiving("GET REQUEST")
                .path("/pact")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body("{\"condition\": true, \"name\": \"tom\"}")
                .given("test POST")
                .uponReceiving("POST REQUEST")
                .method("POST")
                .headers(headers)
                .body("{\"name\": \"Michael\"}")
                .path("/pact")
                .willRespondWith()
                .status(201)
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPact")
    void givenGet_whenSendRequest_shouldReturn200WithProperHeaderAndBody(MockServer mockServer) {
        // when
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockServer.getUrl() + "/pact", String.class);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();
        assertThat(response.getBody()).contains("condition", "true", "name", "tom");

        // and
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        String jsonBody = "{\"name\": \"Michael\"}";

        // when
        ResponseEntity<String> postResponse = new RestTemplate().exchange(mockServer.getUrl() + "/pact", HttpMethod.POST, new HttpEntity<>(jsonBody, httpHeaders), String.class);

        // then
        assertThat(postResponse.getStatusCode().value()).isEqualTo(201);
    }

    @Pact(consumer = "test_consumer")
    public RequestResponsePact createPactForEmployee(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");

        return builder
                .given("test GET employees")
                .uponReceiving("GET REQUEST for employees")
                .path("/api/employees")
                .method("GET")
                .willRespondWith()
                .status(200)
                .headers(headers)
                .body(new PactDslJsonArray()
                        .object()
                        .integerType("id", 1)
                        .stringType("firstName", "John")
                        .stringType("lastName", "Doe")
                        .stringType("email", "john.doe@example.com")
                        .closeObject()
                        .object()
                        .integerType("id", 2)
                        .stringType("firstName", "Jane")
                        .stringType("lastName", "Smith")
                        .stringType("email", "jane.smith@example.com")
                        .closeObject()
                        .object()
                        .integerType("id", 3)
                        .stringType("firstName", "Michael")
                        .stringType("lastName", "Johnson")
                        .stringType("email", "michael.johnson@example.com")
                        .closeObject()
                )
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "createPactForEmployee")
    void givenGet_whenSendRequest_shouldReturn200WithEmployeesData(MockServer mockServer) throws JsonProcessingException {
        // when
        ResponseEntity<String> response = new RestTemplate().getForEntity(mockServer.getUrl() + "/api/employees", String.class);

        // then
        assertThat(response.getStatusCode().value()).isEqualTo(200);
        assertThat(response.getHeaders().get("Content-Type").contains("application/json")).isTrue();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseBody = objectMapper.readTree(response.getBody());

        assertThat(responseBody.isArray()).isTrue();
        assertThat(responseBody).hasSize(3); // Assuming there are 3 employee objects

        // Check if each employee's information is present
        for (JsonNode employeeNode : responseBody) {
            assertThat(employeeNode.get("firstName").asText()).isIn("John", "Jane", "Michael");
            assertThat(employeeNode.get("lastName").asText()).isIn("Doe", "Smith", "Johnson");
            assertThat(employeeNode.get("email").asText()).endsWith("@example.com");
        }
    }

}