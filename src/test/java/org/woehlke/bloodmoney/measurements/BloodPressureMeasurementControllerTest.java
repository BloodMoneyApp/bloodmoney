package org.woehlke.bloodmoney.measurements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// https://www.baeldung.com/spring-boot-testing

//TODO: #34 Test a SpringMVC Controller with Spring Security
@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
public class BloodPressureMeasurementControllerTest {

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

    @PreDestroy
    void runAfterAll() {
        log.info("TEST: runAfterAll");
    }

    @Test
    public void getRootPublic() throws Exception {
        log.info("TEST: shouldReturnDefaultMessage: /");
        this.mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().is3xxRedirection());
        log.info("TEST: shouldReturnDefaultMessage: /login");
        this.mockMvc.perform(get("/login"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Willkommen zu BloodMoney")))
            .andExpect(content().string(containsString("Ihre App um Messwerte zu erfassen")));
    }

    @WithMockUser(username="thomas.woehlke@gmail.com")
    @Test
    public void getrootPrivate() throws Exception {
        log.info("TEST: shouldReturnDefaultMessage: /");
        this.mockMvc.perform(get("/"))
            .andDo(print())
            .andExpect(status().is3xxRedirection());
        log.info("TEST: shouldReturnDefaultMessage: /login");
        this.mockMvc.perform(get("/login"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Willkommen zu BloodMoney")))
            .andExpect(content().string(containsString("Ihre App um Messwerte zu erfassen")));
    }
}
