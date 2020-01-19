package org.woehlke.bloodmoney.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Component
@Validated
@ConfigurationProperties(prefix="org.woehlke.bloodmoney")
public class ApplicationProperties implements Serializable {

    private static final long serialVersionUID = 4480323170764476017L;

    @NotNull
    private String userEmail;

    @NotNull
    private String userPassword;

    @NotNull
    private String userFullname;

    @NotNull
    private Boolean devTesting;

    @NotNull
    private Integer iterations;

    @NotNull
    private Integer hashWidth;

    @NotNull
    private String secret;

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserFullname() {
        return userFullname;
    }

    public void setUserFullname(String userFullname) {
        this.userFullname = userFullname;
    }

    public Boolean getDevTesting() {
        return devTesting;
    }

    public void setDevTesting(Boolean devTesting) {
        this.devTesting = devTesting;
    }

    public Integer getIterations() {
        return iterations;
    }

    public void setIterations(Integer iterations) {
        this.iterations = iterations;
    }

    public Integer getHashWidth() {
        return hashWidth;
    }

    public void setHashWidth(Integer hashWidth) {
        this.hashWidth = hashWidth;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    @Override
    public String toString() {
        return "ApplicationProperties{" +
            "userEmail='" + userEmail + '\'' +
            ", userPassword='" + userPassword + '\'' +
            ", userFullname='" + userFullname + '\'' +
            ", devTesting=" + devTesting +
            ", iterations=" + iterations +
            ", hashWidth=" + hashWidth +
            ", secret='" + secret + '\'' +
            '}';
    }
}
