package org.woehlke.bloodmoney.user.model;

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
//@AllArgsConstructor
public class LoginForm  implements Serializable {

    private static final long serialVersionUID = 8947782653424181984L;

    //TODO: Messages i18n
    @NotNull(message = "Email Address is compulsory")
    @NotBlank(message = "Email Address is compulsory")
    @Email(message = "Email Address is not a valid format")
    private String userEmail;

    //TODO: Messages i18n
    @SafeHtml(whitelistType= SafeHtml.WhiteListType.NONE)
    @NotNull(message = "Password is compulsory")
    @NotBlank(message = "Password is compulsory")
    private String userPassword;

    public LoginForm(
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
