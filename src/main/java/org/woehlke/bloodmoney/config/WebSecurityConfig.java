package org.woehlke.bloodmoney.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.woehlke.bloodmoney.domain.security.UserDetailsService;


@Slf4j
@Configuration
@EnableAsync
@EnableJpaAuditing
@EnableWebMvc
@EnableSpringDataWebSupport
@Import({
  WebMvcConfig.class
})
@EnableConfigurationProperties({
  BloodMoneyProperties.class
})
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class WebSecurityConfig {

  private final UserDetailsService userDetailsService;
  private final BloodMoneyProperties bloodMoneyProperties;

  @Autowired
  public WebSecurityConfig(
    UserDetailsService userDetailsService,
    BloodMoneyProperties bloodMoneyProperties
  ) {
    this.userDetailsService = userDetailsService;
    this.bloodMoneyProperties = bloodMoneyProperties;
  }

  @Primary
  @Bean
  public org.springframework.security.core.userdetails.UserDetailsService userDetailsService() {
    return this.userDetailsService;
  }

  /**
   * @return PasswordEncoder encoder
   * @see <a href="https://asecuritysite.com/encryption/PBKDF2">Encrypt with PBKDF2</a>
   */
  @Bean
  public PasswordEncoder encoder() {
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    log.info(" encoder config ");
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    CharSequence secret = this.bloodMoneyProperties.getWebSecurity().getSecret();
    //int iterations=this.bloodMoneyProperties.getWebSecurity().getIterations();
    int iterations = 185000;
    int saltLength = 8;
    int hashWidth = 256;
    Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm =
      Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512;
    Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(secret, saltLength, iterations, hashWidth);
    encoder.setAlgorithm(secretKeyFactoryAlgorithm);
    encoder.setEncodeHashAsBase64(true);
    log.info("secret:       " + secret);
    log.info("secretLength: " + secret.length());
    log.info("saltLength:   " + saltLength);
    log.info("iterations:   " + iterations);
    log.info("hashWidth:    " + hashWidth);
    log.info("Algorithm:    " + secretKeyFactoryAlgorithm.name());
    log.info("configPW:     " + this.bloodMoneyProperties.getUserConfig().getUserPassword());
    log.info("encodedPW:    " + encoder.encode("Recoil89"));
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    return encoder;
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    log.info(" authenticationProvider ");
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    DaoAuthenticationProvider p = new DaoAuthenticationProvider();
    p.setUserDetailsService(this.userDetailsService);
    p.setPasswordEncoder(encoder());
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    return p;
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    log.info(" securityFilterChain ");
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntMatchersPermitAll:  ");
    for (String urlPath : this.bloodMoneyProperties.getWebSecurity().getAntMatchersPermitAll()) {
      log.info(urlPath);
    }
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntPatternsPublic:  ");
    for (String urlPath : this.bloodMoneyProperties.getWebSecurity().getAntPatternsPublic()) {
      log.info(urlPath);
    }
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntMatchersFullyAuthenticated:  ");
    log.info(this.bloodMoneyProperties.getWebSecurity().getAntMatchersFullyAuthenticated());
    log.info("-------------------------------------------------------------------------------------");
    log.info(" application Properties:  " + this.bloodMoneyProperties.toString());
    log.info("-------------------------------------------------------------------------------------");
    log.info(" configure SecurityFilterChain from HttpSecurity http ");
    http
      .headers((headers) -> headers.disable())
      .authorizeRequests((authorizeRequests) -> authorizeRequests
        .antMatchers(
          this.bloodMoneyProperties.getWebSecurity().getAntPatternsPublic()
        )
        .permitAll()
        .anyRequest()
        .fullyAuthenticated()
      )
      .csrf()
      .and()
      .formLogin((form) -> form
        .loginPage(this.bloodMoneyProperties.getWebSecurity().getLoginPage())
        .usernameParameter(this.bloodMoneyProperties.getWebSecurity().getUsernameParameter())
        .passwordParameter(this.bloodMoneyProperties.getWebSecurity().getPasswordParameter())
        .loginProcessingUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl())
        .failureForwardUrl(this.bloodMoneyProperties.getWebSecurity().getFailureForwardUrl())
        .defaultSuccessUrl(this.bloodMoneyProperties.getWebSecurity().getDefaultSuccessUrl())
        .permitAll()
      )
      .csrf()
      .and()
      .logout((logout) -> logout
        .logoutUrl(this.bloodMoneyProperties.getWebSecurity().getLogoutUrl())
        .deleteCookies(this.bloodMoneyProperties.getWebSecurity().getCookieNamesToClear())
        .invalidateHttpSession(this.bloodMoneyProperties.getWebSecurity().getInvalidateHttpSession())
        .permitAll()
      );
    log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    return http.build();
  }

}
