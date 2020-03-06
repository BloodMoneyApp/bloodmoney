package org.woehlke.bloodmoney.user.account;

import org.woehlke.bloodmoney.user.login.LoginForm;

public interface UserAccountAccessService {

    boolean confirmUserByLoginAndPassword(String userEmail, String userPassword);

    boolean authorize(LoginForm loginForm);
}
