package org.woehlke.bloodmoney.config;


import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Slf4j
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Validated
@Component
@ConfigurationProperties(prefix = "org.woehlke.bloodmoney")
public class BloodMoneyProperties implements Serializable {

  static final long serialVersionUID = 4480323170764476017L;

  @NotNull
  private Boolean devTesting;

  @NotNull
  private Integer testDataHowManyTestData;

  @Valid
  @NotNull
  public UserConfig userConfig;

  @Valid
  @NotNull
  public WebConfig webConfig;

  @Valid
  @NotNull
  public WebSecurity webSecurity;

  @ToString
  @Getter
  @Setter
  @Validated
  public static class UserConfig implements Serializable {

    static final long serialVersionUID = 4480323170764476017L;

    @NotNull
    private String userEmail;

    @NotNull
    private String userPassword;

    @NotNull
    private String userFullname;
  }

  @ToString
  @Getter
  @Setter
  @Validated
  public static class WebConfig implements Serializable {

    static final long serialVersionUID = 4480323170764476017L;

    @NotNull
    private String exportFilename;

    @NotNull
    private String exportFilenameSeparator;

    @NotNull
    private String[] webAddResourceHandlers;

    @NotNull
    private String[] webAddResourceHandlersStatic;
  }

  @ToString
  @Getter
  @Setter
  @Validated
  public static class WebSecurity implements Serializable {

    static final long serialVersionUID = 4480323170764476017L;

    @NotNull
    private String antMatchersFullyAuthenticated;

    @NotNull
    private String[] antPatternsPublic;

    @NotNull
    private String[] antMatchersPermitAll;

    private String[] cookieNamesToClear;

    @NotNull
    private String usernameParameter;

    @NotNull
    private String passwordParameter;

    @NotNull
    private String secret;

    @NotNull
    private Integer iterations;

    @NotNull
    private Integer hashWidth;

    @NotNull
    private Boolean invalidateHttpSession;

    @NotNull
    private String loginProcessingUrl;

    @NotNull
    private String failureForwardUrl;

    @NotNull
    private String defaultSuccessUrl;

    @NotNull
    private String logoutUrl;

    @NotNull
    private String loginPage;
  }
}
