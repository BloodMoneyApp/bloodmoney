package org.woehlke.bloodmoney.config.di;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.woehlke.bloodmoney.user.services.UserAccountSecurityService;

@Configuration
@EnableWebSecurity
@EnableSpringDataWebSupport
@Import({
    BloodMoneyWebMvcConfig.class
})
public class BloodMoneyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers()
            .disable()
            .authorizeRequests()
            .antMatchers(
                mvcConfig().config().properties().getWebSecurity().getAntMatchersPermitAll()
            )
            .permitAll()
            .antMatchers(    mvcConfig().config().properties().getWebSecurity().getAntMatchersFullyAuthenticated())
            .fullyAuthenticated().anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage(mvcConfig().config().properties().getWebSecurity().getLoginPage())
            .usernameParameter(mvcConfig().config().properties().getWebSecurity().getUsernameParameter())
            .passwordParameter(mvcConfig().config().properties().getWebSecurity().getPasswordParameter())
            .loginProcessingUrl(mvcConfig().config().properties().getWebSecurity().getLoginProcessingUrl())
            .failureForwardUrl(mvcConfig().config().properties().getWebSecurity().getFailureForwardUrl())
            .defaultSuccessUrl(mvcConfig().config().properties().getWebSecurity().getDefaultSuccessUrl())
            .successHandler(loginSuccessHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl(mvcConfig().config().properties().getWebSecurity().getLogoutUrl())
            .deleteCookies(mvcConfig().config().properties().getWebSecurity().getDeleteCookies())
            .invalidateHttpSession(mvcConfig().config().properties().getWebSecurity().getInvalidateHttpSession())
            .permitAll();
    }

    @Bean
    public PasswordEncoder encoder(){
        // https://asecuritysite.com/encryption/PBKDF2z
        CharSequence secret=mvcConfig().config().properties().getWebSecurity().getSecret();
        int iterations=mvcConfig().config().properties().getWebSecurity().getIterations();
        int hashWidth=mvcConfig().config().properties().getWebSecurity().getHashWidth();
        Pbkdf2PasswordEncoder encoder = (new Pbkdf2PasswordEncoder(secret,iterations,hashWidth));
        encoder.setEncodeHashAsBase64(true);
        return encoder;
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return auth.userDetailsService(userAccountSecurityService).passwordEncoder(encoder()).and().build();
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManager());
        filter.setFilterProcessesUrl(mvcConfig().config().properties().getWebSecurity().getLoginProcessingUrl());
        return filter;
    }

    @Bean
    public BloodMoneyWebMvcConfig mvcConfig(){
        return this.mvcConfig;
    }

    private final AuthenticationManagerBuilder auth;
    private final AuthenticationSuccessHandler loginSuccessHandler;
    private final UserDetailsService userAccountSecurityService;
    private final BloodMoneyWebMvcConfig mvcConfig;

    @Autowired
    public BloodMoneyWebSecurityConfig(
        AuthenticationManagerBuilder auth,
        AuthenticationSuccessHandler loginSuccessHandler,
        UserAccountSecurityService userAccountSecurityService,
        BloodMoneyWebMvcConfig bloodMoneyWebMvcConfig
    ) {
        this.auth = auth;
        this.loginSuccessHandler = loginSuccessHandler;
        this.userAccountSecurityService = userAccountSecurityService;
        this.mvcConfig = bloodMoneyWebMvcConfig;
    }


}
