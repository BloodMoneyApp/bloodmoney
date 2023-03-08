package org.woehlke.bloodmoney.frontend;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;
import static org.springframework.web.servlet.function.RequestPredicates.headers;

//TODO #35 Test a Rest Controller with Spring Security
@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class MeasurementEntityExportControllerTest {

    @Autowired
    private MeasurementExportController measurementExportController;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @PostConstruct
    public void runBeforeAll() {
        log.info("==================================================================================================");
        log.info("TEST: runBeforeAll");
        mockMvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
        Assertions.assertNotNull(mockMvc, "runBeforeAll() context -> mockMvc");
        log.info("==================================================================================================");
    }

    @PreDestroy
    public void runAfterAll() {
        log.info("==================================================================================================");
        log.info("TEST: runAfterAll");
        log.info("==================================================================================================");
    }

    @WithMockUser(username = "thomas.woehlke@gmail.com")
    @Test
    public void exportCSVTest() throws Exception  {
        log.info("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        log.info("TEST: exportCSVTest");
        log.info("--------------------------------------------------------------------------------------------------");
        this.mockMvc.perform(get("/measurement/export/all"))
            .andDo(print())
            .andExpectAll(
                status().isOk(),
                content().contentType("text/csv"),
                content().string(containsString(
                    "CREATED;DATE;DIASTOLICBOTTOMNUMBER;HOSTNAME;HOSTNAMECANONICAL;IP;PULSE;SITUATION;SYSTOLICTOPNUMBER;TIME;UPDATED;WEIGHT"
                )));
        log.info("--------------------------------------------------------------------------------------------------");
    }
}
