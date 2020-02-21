package org.woehlke.bloodmoney.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.woehlke.bloodmoney.application.BloodMoneyProperties;

@Configuration
@EnableAsync
@EnableJpaRepositories({
    "org.woehlke.bloodmoney.oodm.repositories"
})
@EnableConfigurationProperties({
    BloodMoneyProperties.class
})
@EnableAutoConfiguration
public class BloodMoneyConfig {

    @Bean
    public BloodMoneyProperties properties(){
        return this.bloodMoneyProperties;
    }

    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public BloodMoneyConfig(BloodMoneyProperties bloodMoneyProperties) {
        this.bloodMoneyProperties = bloodMoneyProperties;
    }
}
