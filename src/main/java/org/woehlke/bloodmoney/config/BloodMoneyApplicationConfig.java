package org.woehlke.bloodmoney.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@EnableJpaAuditing
@EnableJpaRepositories({
    "org.woehlke.bloodmoney.oodm.repositories"
})
@EnableConfigurationProperties({
        BloodMoneyProperties.class,
})
public class BloodMoneyApplicationConfig {
}
