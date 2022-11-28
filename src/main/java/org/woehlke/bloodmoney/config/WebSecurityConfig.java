package org.woehlke.bloodmoney.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
@Import({
    BloodMoneyWebMvcConfig.class
})
public class WebSecurityConfig {

  private final BloodMoneyProperties bloodMoneyProperties;

  @Autowired
  public WebSecurityConfig(BloodMoneyProperties bloodMoneyProperties) {
    this.bloodMoneyProperties = bloodMoneyProperties;
  }

  /**
   * @see org.woehlke.bloodmoney.domain.security.user.BloodMoneyUserDetailsServiceServiceImpl
   * @return PasswordEncoder encoder
   */
  @Bean
  public PasswordEncoder encoder(){
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    log.info(" encoder config ");
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    CharSequence secret=this.bloodMoneyProperties.getWebSecurity().getSecret();
    int iterations=this.bloodMoneyProperties.getWebSecurity().getIterations();
    //this.bloodMoneyProperties.getWebSecurity().getIterations();
    int saltLength=8;
    Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm =
      Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512;
    Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(
      secret, saltLength, iterations, secretKeyFactoryAlgorithm
    );
    encoder.setEncodeHashAsBase64(true);
    log.info("secret:       "+secret);
    log.info("secretLength: "+secret.length());
    log.info("saltLength:   "+saltLength);
    log.info("iterations:   "+iterations);
    log.info("Algorithm:    "+Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512.name());
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    return encoder;
  }

  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.headers((headers) -> headers
      .disable()
    );
    http
      .authorizeRequests((authorize) -> authorize
        .requestMatchers (
          "/bloodmoney",
          "/bloodmoney/**",
          "/icon/**",
          "/icon",
          "/languages",
          "/languages/**",
          "/favicon.ico",
          "/webjars/**",
          "/webjars",
          "/test*/**",
          "/rest",
          "/rest/**",
          "/bpm",
          "/login*",
          "/register*",
          "/confirm*/**",
          "/resetPassword*",
          "/passwordResetConfirm*/**",
          "/error*",
          "/fehler*"
        ).anonymous().anyRequest()
      );
    http
      .formLogin((formLogin) -> formLogin
          //.usernameParameter("username")
          //.passwordParameter("password")
          .loginPage(this.bloodMoneyProperties.getWebSecurity().getLoginPage())
          //.failureUrl("/authentication/login?failed")
          //.loginProcessingUrl("/login")
      );
    return http.build();
  }
}
