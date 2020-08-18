package org.woehlke.bloodmoney.user;

import lombok.*;
import org.hibernate.validator.constraints.SafeHtml;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class LoginFormBean implements Serializable {

    private static final long serialVersionUID = 8947782653424181984L;

    //TODO: Messages i18n
    @NotNull(message = "Email Address is compulsory")
    @NotBlank(message = "Email Address is compulsory")
    @Email(message = "Email Address is not a valid format")
    private String userEmail;

    //TODO: Messages i18n
    @SuppressWarnings("deprecation")
    @SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
    @NotNull(message = "Password is compulsory")
    @NotBlank(message = "Password is compulsory")
    private String userPassword;

    @SuppressWarnings("deprecation")
    public LoginFormBean(
        @NotNull(message = "Email Address is compulsory")
        @NotBlank(message = "Email Address is compulsory")
        @Email(message = "Email Address is not a valid format") String userEmail,
        @SafeHtml(whitelistType = SafeHtml.WhiteListType.NONE)
        @NotNull(message = "Password is compulsory")
        @NotBlank(message = "Password is compulsory") String userPassword) {
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "LoginForm{" +
            "userEmail='" + userEmail + '\'' +
            ", userPassword='***************" + '\'' +
            '}';
    }
}
