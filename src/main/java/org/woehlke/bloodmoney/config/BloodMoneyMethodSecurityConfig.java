package org.woehlke.bloodmoney.config;


import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({
    BloodMoneyWebSecurityConfig.class
})
@EnableAutoConfiguration
public class BloodMoneyMethodSecurityConfig {
}
