package org.woehlke.bloodmoney.config.di;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

@Profile({"default","heroku"})
@Configuration
@EnableAsync
@EnableJpaRepositories({
    "org.woehlke.bloodmoney.oodm.repositories"
})
@EnableConfigurationProperties({
    BloodMoneyProperties.class
})
public class BloodMoneyApplicationDefaultConfig {
}
