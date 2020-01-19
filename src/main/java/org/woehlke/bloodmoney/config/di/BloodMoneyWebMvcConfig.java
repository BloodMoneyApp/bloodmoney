package org.woehlke.bloodmoney.config.di;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.thymeleaf.dialect.springdata.SpringDataDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import java.util.Locale;

@Configuration
@EnableWebMvc
@EnableSpringDataWebSupport
@Import({
    BloodMoneyConfig.class
})
public class BloodMoneyWebMvcConfig /* extends WebMvcConfigurerAdapter */ implements WebMvcConfigurer {

    @Bean
    public Java8TimeDialect java8TimeDialect() {
        return new Java8TimeDialect();
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.GERMAN);
        return slr;
    }

    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }

    @Bean
    public SpringDataDialect springDataDialect() {
        return new SpringDataDialect();
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        for(String key: config().properties().getWebConfig().getWebAddResourceHandlers()){
            registry.addResourceHandler("/"+key+"*").addResourceLocations("/"+key);
            registry.addResourceHandler("/"+key+"**").addResourceLocations("/"+key);
        }
        for(String key: config().properties().getWebConfig().getWebAddResourceHandlersStatic()){
            registry.addResourceHandler("/"+key+"*").addResourceLocations("classpath:/static/"+key);
            registry.addResourceHandler("/"+key+"**").addResourceLocations("classpath:/static/"+key);
        }
    }

    @Bean
    public BloodMoneyConfig config(){
        return this.bloodMoneyConfig;
    }

    private final BloodMoneyConfig bloodMoneyConfig;

    @Autowired
    public BloodMoneyWebMvcConfig(BloodMoneyConfig bloodMoneyConfig) {
        this.bloodMoneyConfig = bloodMoneyConfig;
    }
}
