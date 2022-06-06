package org.woehlke.bloodmoney.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.woehlke.bloodmoney.user.BloodMoneyUserAccountDetailsService;
import org.woehlke.bloodmoney.user.UserAccountLoginSuccessService;

@Configuration
@EnableWebSecurity
@EnableSpringDataWebSupport
@Import({
    BloodMoneyWebMvcConfig.class
})
@EnableWebMvc
@EnableAutoConfiguration
public class BloodMoneyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    //private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final UserDetailsService userAccountSecurityService;

    //private final UserAccountLoginSuccessService userAccountLoginSuccessService;
    //private final LocaleResolver localeResolver;

    private final BloodMoneyProperties bloodMoneyProperties;

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
            .defaultSuccessUrl(this.bloodMoneyProperties.getWebSecurity().getDefaultSuccessUrl())
            .failureForwardUrl(this.bloodMoneyProperties.getWebSecurity().getFailureForwardUrl())
            .loginProcessingUrl(this.bloodMoneyProperties.getWebSecurity().getLoginProcessingUrl())
            //.successHandler(this.authenticationSuccessHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl(this.bloodMoneyProperties.getWebSecurity().getLogoutUrl())
            .deleteCookies(this.bloodMoneyProperties.getWebSecurity().getDeleteCookies())
            .invalidateHttpSession(this.bloodMoneyProperties.getWebSecurity().getInvalidateHttpSession())
            .permitAll();
    }


    /**
     * https://asecuritysite.com/encryption/PBKDF2
     * @return PasswordEncoder encoder
     */
    @Bean
    public PasswordEncoder encoder(){
        CharSequence secret=this.bloodMoneyProperties.getWebSecurity().getSecret();
        int iterations=this.bloodMoneyProperties.getWebSecurity().getIterations();
        int hashWidth=this.bloodMoneyProperties.getWebSecurity().getHashWidth();
        Pbkdf2PasswordEncoder encoder = (new Pbkdf2PasswordEncoder(secret,iterations,hashWidth));
        encoder.setEncodeHashAsBase64(true);
        return encoder;
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(  this.userAccountSecurityService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
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

    @Autowired
    public BloodMoneyWebSecurityConfig(
        AuthenticationManagerBuilder auth,
        //AuthenticationSuccessHandler authenticationSuccessHandler,
        BloodMoneyUserAccountDetailsService bloodMoneyUserAccountDetailsService,
        //UserAccountLoginSuccessService userAccountLoginSuccessService,
        //LocaleResolver localeResolver,
        BloodMoneyProperties bloodMoneyProperties) {
        this.authenticationManagerBuilder = auth;
        //this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.userAccountSecurityService = bloodMoneyUserAccountDetailsService;
        //this.userAccountLoginSuccessService = userAccountLoginSuccessService;
        //this.localeResolver = localeResolver;
        this.bloodMoneyProperties = bloodMoneyProperties;
    }
}
