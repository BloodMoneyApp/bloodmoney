package org.woehlke.bloodmoney.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.woehlke.bloodmoney.user.BloodMoneyUserAccountDetailsService;

@Configuration
@EnableWebSecurity
@EnableSpringDataWebSupport
@Import({
    BloodMoneyWebMvcConfig.class
})
@EnableAutoConfiguration
public class BloodMoneyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers()
            .disable()
            .authorizeRequests()
            .antMatchers(
                this.bloodMoneyProperties.getWebSecurity().getAntMatchersPermitAll()
            )
            .permitAll()
            .antMatchers(
                this.bloodMoneyProperties.getWebSecurity().getAntMatchersFullyAuthenticated()
            )
            .fullyAuthenticated().anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage(
                this.bloodMoneyProperties.getWebSecurity().getLoginPage()
            )
            .usernameParameter(this.bloodMoneyProperties.getWebSecurity().getUsernameParameter())
            .passwordParameter(this.bloodMoneyProperties.getWebSecurity().getPasswordParameter())
            .loginProcessingUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl())
            .failureForwardUrl(this.bloodMoneyProperties.getWebSecurity().getFailureForwardUrl())
            .defaultSuccessUrl(this.bloodMoneyProperties.getWebSecurity().getDefaultSuccessUrl())
            .successHandler(this.authenticationSuccessHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl(this.bloodMoneyProperties.getWebSecurity().getLogoutUrl())
            .deleteCookies(this.bloodMoneyProperties.getWebSecurity().getDeleteCookies())
            .invalidateHttpSession(this.bloodMoneyProperties.getWebSecurity().getInvalidateHttpSession())
            .permitAll();
    }

    @Bean
    public PasswordEncoder encoder(){
        // https://asecuritysite.com/encryption/PBKDF2z
        CharSequence secret=this.bloodMoneyProperties.getWebSecurity().getSecret();
        int iterations=this.bloodMoneyProperties.getWebSecurity().getIterations();
        int hashWidth=this.bloodMoneyProperties.getWebSecurity().getHashWidth();
        Pbkdf2PasswordEncoder encoder = (new Pbkdf2PasswordEncoder(secret,iterations,hashWidth));
        encoder.setEncodeHashAsBase64(true);
        return encoder;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return this.authenticationManagerBuilder.userDetailsService(userAccountSecurityService).passwordEncoder(encoder()).and().build();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl());
        return filter;
    }

    @Bean
    public BloodMoneyWebMvcConfig mvcConfig(){
        return this.mvcConfig;
    }


    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final UserDetailsService userAccountSecurityService;
    private final BloodMoneyProperties bloodMoneyProperties;
    private final BloodMoneyWebMvcConfig mvcConfig;

    @Autowired
    public BloodMoneyWebSecurityConfig(
        AuthenticationManagerBuilder auth,
        AuthenticationSuccessHandler authenticationSuccessHandler,
        BloodMoneyUserAccountDetailsService bloodMoneyUserAccountDetailsService,
        BloodMoneyProperties bloodMoneyProperties,
        BloodMoneyWebMvcConfig bloodMoneyWebMvcConfig
    ) {
        this.authenticationManagerBuilder = auth;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userAccountSecurityService = bloodMoneyUserAccountDetailsService;
        this.bloodMoneyProperties = bloodMoneyProperties;
        this.mvcConfig = bloodMoneyWebMvcConfig;
    }

}
