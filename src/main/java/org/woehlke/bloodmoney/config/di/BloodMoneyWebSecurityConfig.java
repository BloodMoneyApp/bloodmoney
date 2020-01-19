package org.woehlke.bloodmoney.config.di;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.services.UserAccountSecurityService;

@Configuration
@EnableWebSecurity
@EnableSpringDataWebSupport
@EnableConfigurationProperties({
    BloodMoneyProperties.class
})
@Import({
    BloodMoneyApplicationConfig.class,
    BloodMoneyWebMvcConfig.class
})
public class BloodMoneyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers().disable()
            .authorizeRequests()
            .antMatchers(
                "/webjars/**", "/css/**", "/img/**", "/js/**", "/favicon.ico",
                "/test*/**", "/login*", "/register*", "/confirm*/**",
                "/resetPassword*", "/passwordResetConfirm*/**", "/error*"
            )
            .permitAll()
            .antMatchers("/**").fullyAuthenticated().anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login")
            .usernameParameter("userEmail").passwordParameter("userPassword")
            .loginProcessingUrl("/j_spring_security_check")
            .failureForwardUrl("/login?login_error=1")
            .defaultSuccessUrl("/")
            .successHandler(loginSuccessHandler)
            .permitAll()
            .and()
            .logout()
            .logoutUrl("/logout")
            .deleteCookies("JSESSIONID")
            .invalidateHttpSession(true)
            .permitAll();
    }

    @Bean
    public PasswordEncoder encoder(){
        // https://asecuritysite.com/encryption/PBKDF2z
        CharSequence secret=this.bloodMoneyProperties.getSecret();
        int iterations=this.bloodMoneyProperties.getIterations();
        int hashWidth=this.bloodMoneyProperties.getHashWidth();
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
        filter.setFilterProcessesUrl("/j_spring_security_check");
        return filter;
    }

    private final AuthenticationManagerBuilder auth;
    private final AuthenticationSuccessHandler loginSuccessHandler;
    private final UserDetailsService userAccountSecurityService;
    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public BloodMoneyWebSecurityConfig(
        AuthenticationManagerBuilder auth,
        AuthenticationSuccessHandler loginSuccessHandler,
        UserAccountSecurityService userAccountSecurityService,
        BloodMoneyProperties bloodMoneyProperties
    ) {
        this.auth = auth;
        this.loginSuccessHandler = loginSuccessHandler;
        this.userAccountSecurityService = userAccountSecurityService;
        this.bloodMoneyProperties = bloodMoneyProperties;
    }
}
