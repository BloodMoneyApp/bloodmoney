package org.woehlke.bloodmoney.user;

import org.woehlke.bloodmoney.user.LoginForm;

public interface UserAccountAccessService {

    boolean confirmUserByLoginAndPassword(String userEmail, String userPassword);

    boolean authorize(LoginForm loginForm);
}
