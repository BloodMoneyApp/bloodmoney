package org.woehlke.bloodmoney.oodm.model;

import lombok.*;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Locale;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
//@AllArgsConstructor
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 4168992193351369032L;

    @Email
    private String userEmail;

    @SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
    private String userPassword;

    @SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
    private String userFullname;

    @NotNull
    private Locale defaultLanguage=Locale.GERMAN;

    @NotNull
    private Boolean accountNonExpired=true;

    @NotNull
    private Boolean accountNonLocked=true;

    @NotNull
    private Boolean credentialsNonExpired=true;

    @NotNull
    private Boolean enabled=true;

    public UserAccount(
        @Email String userEmail,
        @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE) String userPassword,
        @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE) String userFullname
    ) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userFullname = userFullname;
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
