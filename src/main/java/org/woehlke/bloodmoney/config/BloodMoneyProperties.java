package org.woehlke.bloodmoney.config;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.java.Log;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Log
@ToString
@Component
@Validated
@ConfigurationProperties(prefix="org.woehlke.bloodmoney")
public class BloodMoneyProperties implements Serializable {

    private static final long serialVersionUID = 4480323170764476017L;

    @Email
    @NotBlank
    private String userEmail;

    @NotBlank
    private String userPassword;

    @NotBlank
    private String userFullname;

    @NotNull
    private Boolean devTesting;

    @NotNull
    private Integer iterations;

    @NotNull
    private Integer hashWidth;

    @NotBlank
    private String secret;

    @NotBlank
    private String exportFilename;

    @NotBlank
    private String exportFilenameSeparator;

}
