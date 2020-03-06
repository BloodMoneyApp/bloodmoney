package org.woehlke.bloodmoney.measurements;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.woehlke.bloodmoney.user.session.UserSessionService;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BloodPressureMeasurementController.class)
public class WebMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BloodPressureMeasurementService bloodPressureMeasurementService;

    @MockBean
    private UserSessionService userSessionService;

    @Test
    public void greetingShouldReturnMessageFromService() throws Exception {
        when(bloodPressureMeasurementService.getAll()).thenReturn(new ArrayList<BloodPressureMeasurement>());
        this.mockMvc.perform(get("/greeting")).andDo(print()).andExpect(status().isOk())
            .andExpect(content().string(containsString("Hello, Mock")));
    }
}
