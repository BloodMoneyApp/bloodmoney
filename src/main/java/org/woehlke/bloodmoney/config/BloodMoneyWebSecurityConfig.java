package org.woehlke.bloodmoney.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@Import({
    BloodMoneyWebMvcConfig.class
})
@EnableWebSecurity
public class BloodMoneyWebSecurityConfig /* extends WebSecurityConfigurerAdapter implements WebSecurityConfigurer<WebSecurity>  */ {

    private final AuthenticationManagerBuilder auth;
    private final UserDetailsService bloodMoneyUserDetailsService;
    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public BloodMoneyWebSecurityConfig(
      AuthenticationManagerBuilder auth,
      UserDetailsService bloodMoneyUserDetailsService,
      BloodMoneyProperties bloodMoneyProperties
    ) {
      this.auth = auth;
      this.bloodMoneyUserDetailsService = bloodMoneyUserDetailsService;
      this.bloodMoneyProperties = bloodMoneyProperties;
    }

  /**
     * @see <a href="https://asecuritysite.com/encryption/PBKDF2">Encrypt with PBKDF2</a>
     * @return PasswordEncoder encoder
     */
    @Bean
    public PasswordEncoder encoder(){
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      log.info(" encoder config ");
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      CharSequence secret=this.bloodMoneyProperties.getWebSecurity().getSecret();
      //int iterations=this.bloodMoneyProperties.getWebSecurity().getIterations();
      int iterations=185000;
      int saltLength=8;
      int hashWidth=256;
      Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm secretKeyFactoryAlgorithm =
        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA512;
      Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder(secret, saltLength, iterations, hashWidth);
      encoder.setAlgorithm(secretKeyFactoryAlgorithm);
      encoder.setEncodeHashAsBase64(true);
      log.info("secret:       "+secret);
      log.info("secretLength: "+secret.length());
      log.info("saltLength:   "+saltLength);
      log.info("iterations:   "+iterations);
      log.info("hashWidth:    "+hashWidth);
      log.info("Algorithm:    "+secretKeyFactoryAlgorithm.name());
      log.info("configPW:     "+this.bloodMoneyProperties.getUserConfig().getUserPassword());
      log.info("encodedPW:    "+encoder.encode("Recoil89"));
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      return encoder;
    }

    @Bean
    public DaoAuthenticationProvider userDetailsAuthProvider() {
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info(" userDetailsAuthProvider ");
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(this.bloodMoneyUserDetailsService);
        authProvider.setPasswordEncoder(encoder());
        log.info(" userDetailsAuthProvider "+authProvider.toString());
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return authProvider;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      log.info(" webSecurityCustomizer: web.ignoring().antMatchers ");
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      for(String urlPath: this.bloodMoneyProperties.getWebSecurity().getAntMatchersIgnore()){
        log.info(urlPath);
      }
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      return (web) -> web.ignoring().antMatchers(
        this.bloodMoneyProperties.getWebSecurity().getAntMatchersIgnore()
      );
    }
/*
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationManagerBuilder amb) throws Exception {
      return amb.authenticationProvider(userDetailsAuthProvider()).build();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
      UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
      filter.setAuthenticationManager(authenticationManager(this.auth));
      filter.setFilterProcessesUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl());
      return filter;
    }
*/

  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    log.info(" securityFilterChain ");
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntMatchersPermitAll:  ");
    for(String urlPath: this.bloodMoneyProperties.getWebSecurity().getAntMatchersPermitAll()){
      log.info(urlPath);
    }
    log.info("-------------------------------------------------------------------------------------");
    log.info(" getAntMatchersFullyAuthenticated:  ");
    log.info(this.bloodMoneyProperties.getWebSecurity().getAntMatchersFullyAuthenticated());
    log.info("-------------------------------------------------------------------------------------");
    log.info(" application Properties:  "+this.bloodMoneyProperties.toString());
    log.info("-------------------------------------------------------------------------------------");
    log.info(" configure SecurityFilterChain from HttpSecurity http ");
    http
      .authorizeHttpRequests((authorizeRequests) -> authorizeRequests
        .antMatchers(
          this.bloodMoneyProperties.getWebSecurity().getAntMatchersPermitAll()
        ).permitAll()
      )
      .formLogin((form) -> form
        .loginPage(this.bloodMoneyProperties.getWebSecurity().getLoginPage())
        .usernameParameter(this.bloodMoneyProperties.getWebSecurity().getUsernameParameter())
        .passwordParameter(this.bloodMoneyProperties.getWebSecurity().getPasswordParameter())
        .defaultSuccessUrl(this.bloodMoneyProperties.getWebSecurity().getDefaultSuccessUrl())
        .failureForwardUrl(this.bloodMoneyProperties.getWebSecurity().getFailureForwardUrl())
        .loginProcessingUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl())
        .permitAll()
      )
      .logout((logout) -> logout
        .logoutUrl(this.bloodMoneyProperties.getWebSecurity().getLogoutUrl())
        .deleteCookies(this.bloodMoneyProperties.getWebSecurity().getDeleteCookies())
        .invalidateHttpSession(this.bloodMoneyProperties.getWebSecurity().getInvalidateHttpSession())
        .permitAll()
      );
    log.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    return http.build();
  }

}
