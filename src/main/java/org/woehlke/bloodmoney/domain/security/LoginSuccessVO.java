package org.woehlke.bloodmoney.domain.security;

import lombok.*;
//import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Locale;

@Getter
@Setter
@EqualsAndHashCode
@Deprecated
public class LoginSuccessVO implements Serializable {

    private static final long serialVersionUID = 4168992193351369032L;

    @Email
    @NotNull
    @NotBlank
    private String userEmail;

    @NotNull
    @NotBlank
    //@SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
    private String userPassword;

    @NotNull
    //@SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
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

    public LoginSuccessVO(
        @Email String userEmail,
        //@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
        String userPassword,
        //@SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
        String userFullname
    ) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userFullname = userFullname;
    }

    @Override
    public String toString() {
        return userEmail;
    }
}
