package org.woehlke.bloodmoney.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.woehlke.bloodmoney.domain.security.user.BloodMoneyUserDetailsServiceService;


@Slf4j
@Getter
@ActiveProfiles("default")
@SpringBootTest
public class BloodMoneyWebSecurityConfigTest {

  @Autowired
  private WebApplicationContext context;

  @Autowired
  private BloodMoneyProperties bloodMoneyProperties;

  @Autowired
  private AuthenticationManagerBuilder authenticationManagerBuilder;

  @Autowired
  private BloodMoneyUserDetailsServiceService bloodMoneyUserDetailsServiceService;

  private MockMvc mockMvc;

  @Test
  public void test(){
    BloodMoneyWebSecurityConfig cfg = new BloodMoneyWebSecurityConfig(
      authenticationManagerBuilder,
      bloodMoneyUserDetailsServiceService,
      bloodMoneyProperties
    );
  }
}
