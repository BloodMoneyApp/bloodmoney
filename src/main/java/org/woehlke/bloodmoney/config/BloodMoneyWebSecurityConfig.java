package org.woehlke.bloodmoney.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.woehlke.bloodmoney.domain.security.user.BloodMoneyUserDetailsServiceService;


@Slf4j
@Configuration
@Import({
    BloodMoneyWebMvcConfig.class
})
@EnableWebSecurity
//@SuppressWarnings("deprecation")
//@EnableSpringDataWebSupport
//@EnableWebMvc
//@EnableAutoConfiguration
public class BloodMoneyWebSecurityConfig /* extends WebSecurityConfigurerAdapter*/  {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final UserDetailsService userAccountSecurityService;
    private final BloodMoneyProperties bloodMoneyProperties;

    /**
     * @see <a href="https://asecuritysite.com/encryption/PBKDF2_2">Encrypt with PBKDF2</a>
     * @see <a href="https://8gwifi.org/pbkdf.jsp">Encrypt with PBKDF2</a>
     * @see <a href="https://neurotechnics.com/tools/pbkdf2-test>Encrypt with PBKDF2</a>
     * @return PasswordEncoder encoder
     */
    @Bean
    public PasswordEncoder encoder(){
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      log.info(" encoder config ");
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      CharSequence secret=this.bloodMoneyProperties.getWebSecurity().getSecret();
      int iterations=this.bloodMoneyProperties.getWebSecurity().getIterations();
      this.bloodMoneyProperties.getWebSecurity().getIterations();
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

    /*
    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      log.info(" authenticationManager ");
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      AuthenticationManager am = this.authenticationManagerBuilder
        .userDetailsService(userAccountSecurityService)
        .passwordEncoder(encoder()).and().build();
      log.info(" authenticationManager " + am.toString());
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      return am;
    }
    */

    /*
    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl());
        return filter;
    }
    */

  @Bean
  public DaoAuthenticationProvider authProvider() {
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    log.info(" authProvider ");
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(  this.userAccountSecurityService);
    authProvider.setPasswordEncoder(encoder());
    log.info(" authProvider "+authProvider.toString());
    log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    return authProvider;
  }

  /*
  @Bean
  public RememberMeServices rememberMeServices() {
    String myKey = this.bloodMoneyProperties.getWebSecurity().getSecret();
    return new TokenBasedRememberMeServices(myKey, userAccountSecurityService);
  }

  @Bean
  public AnonymousAuthenticationFilter anonymousAuthFilter(){
    String key =this.bloodMoneyProperties.getWebSecurity().getSecret();
    AnonymousAuthenticationFilter f = new AnonymousAuthenticationFilter(key);
    return f;
  }

  @Bean
  public AnonymousAuthenticationProvider anonymousAuthenticationProvider(){
    String key =this.bloodMoneyProperties.getWebSecurity().getSecret();
    AnonymousAuthenticationProvider p = new AnonymousAuthenticationProvider(key);
    return p;
  }
  */

  //@Override
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
        requestCache.setMatchingRequestParameterName("continue");
        http
          /*
          .headers((headers) -> headers
            .contentTypeOptions()
            .and()
            .xssProtection()
            .and()
            .cacheControl()
            .and()
            .httpStrictTransportSecurity()
            .and()
            .frameOptions()
          )*/
          .headers((headers) -> headers
            .disable()
          )
          .securityContext((securityContext) -> securityContext
            .requireExplicitSave(true)
          )
          /*
          .requestCache((cache) -> cache
            .requestCache(requestCache)
          )
          */
          .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
            .requestMatchers(
              this.bloodMoneyProperties.getWebSecurity().getAntMatchersPermitAll()
            ).permitAll().anyRequest().authenticated().and()
          )
          /*
          .authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
              .requestMatchers(
                this.bloodMoneyProperties.getWebSecurity().getAntMatchersPermitAll()
              ).fullyAuthenticated().anyRequest().authenticated()
          )
           */
          .formLogin((formLogin) -> formLogin
            .loginPage(
              this.bloodMoneyProperties.getWebSecurity().getLoginPage()
            )
            .usernameParameter(this.bloodMoneyProperties.getWebSecurity().getUsernameParameter())
            .passwordParameter(this.bloodMoneyProperties.getWebSecurity().getPasswordParameter())
            .defaultSuccessUrl(this.bloodMoneyProperties.getWebSecurity().getDefaultSuccessUrl())
            .failureForwardUrl(this.bloodMoneyProperties.getWebSecurity().getFailureForwardUrl())
            .loginProcessingUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl())
            .permitAll().and()
          )
          .logout((logout) -> logout
            .logoutUrl(this.bloodMoneyProperties.getWebSecurity().getLogoutUrl())
            .deleteCookies(this.bloodMoneyProperties.getWebSecurity().getDeleteCookies())
            .invalidateHttpSession(this.bloodMoneyProperties.getWebSecurity().getInvalidateHttpSession())
            .permitAll().and()
          );
          return http.build();
    }

    @Autowired
    public BloodMoneyWebSecurityConfig(
        AuthenticationManagerBuilder authenticationManagerBuilder,
        BloodMoneyUserDetailsServiceService bloodMoneyUserDetailsServiceService,
        BloodMoneyProperties bloodMoneyProperties
    ) {
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.userAccountSecurityService = bloodMoneyUserDetailsServiceService;
        this.bloodMoneyProperties = bloodMoneyProperties;
    }
}
