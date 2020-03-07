package org.woehlke.bloodmoney.measurements;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//TODO: #34 Test a SpringMVC Controller with Spring Security
@Slf4j
@Getter
@ActiveProfiles("dev")
@SpringBootTest
@AutoConfigureMockMvc
@WebMvcTest(BloodPressureMeasurementController.class)
public class BloodPressureMeasurementControllerTest {

    @Autowired
    private MockMvc mockMvc;

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
