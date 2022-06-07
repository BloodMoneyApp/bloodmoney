package org.woehlke.bloodmoney.measurements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BloodPressureMeasurementEntityResourceTest {

    @SuppressWarnings("deprecation")
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BloodPressureMeasurementResource bloodPressureMeasurementResource;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @PostConstruct
    public void runBeforeAll() {
        log.info("TEST: runBeforeAll");
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
        Assertions.assertNotNull(mockMvc,"runBeforeAll() context -> mockMvc");
    }

    private final String host = "http://localhost:";
    private final String path = "/rest/measurement";;

    @PreDestroy
    public void runAfterAll() throws Exception {
        log.info("TEST: runAfterAll");
    }

    @Test
    public void getAllTest() throws Exception {
        String url = host + port + path + "/all";
        log.info("TEST: getAllTest url="+url);
    }

    @Test
    public void updateTest() throws Exception {
        String url = host + port + path + "/{id}";
        log.info("TEST: updateTest url="+url);
    }

    @Test
    public void deleteTest() throws Exception {
        String url = host + port + path + "/";
        log.info("TEST: deleteTest url="+url);
    }

    @Test
    public void addTest() throws Exception {
        String url = host + port + path + "/";
        log.info("TEST: addTest url="+url);
    }
}
