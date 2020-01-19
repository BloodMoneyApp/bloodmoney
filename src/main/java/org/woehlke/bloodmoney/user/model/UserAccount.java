package org.woehlke.bloodmoney.user.model;

import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Locale;
import java.util.Objects;

public class UserAccount implements Serializable {

    private static final long serialVersionUID = 4168992193351369032L;

    @Email
    private String userEmail;

    @SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
    private String userPassword;

    @SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
    private String userFullname;

    private Locale defaultLanguage=Locale.GERMAN;

    @NotNull
    private Boolean accountNonExpired=true;

    @NotNull
    private Boolean accountNonLocked=true;

    @NotNull
    private Boolean credentialsNonExpired=true;

    @NotNull
    private Boolean enabled=true;

    public UserAccount(@Email String userEmail, @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE) String userPassword, @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE) String userFullname) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userFullname = userFullname;
    }

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

    public Locale getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(Locale defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public Boolean getAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserAccount)) return false;
        UserAccount that = (UserAccount) o;
        return getUserEmail().equals(that.getUserEmail()) &&
            getUserPassword().equals(that.getUserPassword()) &&
            getUserFullname().equals(that.getUserFullname()) &&
            getDefaultLanguage().equals(that.getDefaultLanguage()) &&
            getAccountNonExpired().equals(that.getAccountNonExpired()) &&
            getAccountNonLocked().equals(that.getAccountNonLocked()) &&
            getCredentialsNonExpired().equals(that.getCredentialsNonExpired()) &&
            getEnabled().equals(that.getEnabled());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserEmail(), getUserPassword(), getUserFullname(), getDefaultLanguage(), getAccountNonExpired(), getAccountNonLocked(), getCredentialsNonExpired(), getEnabled());
    }

    @Override
    public String toString() {
        return "UserAccount{" +
            "userEmail='" + userEmail + '\'' +
            ", userPassword='***********" + '\'' +
            ", userFullname='" + userFullname + '\'' +
            ", defaultLanguage=" + defaultLanguage +
            ", accountNonExpired=" + accountNonExpired +
            ", accountNonLocked=" + accountNonLocked +
            ", credentialsNonExpired=" + credentialsNonExpired +
            ", enabled=" + enabled +
            '}';
    }
}
