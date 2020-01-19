package org.woehlke.bloodmoney.user.services;

import org.woehlke.bloodmoney.user.model.LoginForm;

public interface UserAccountAccessService {

    boolean confirmUserByLoginAndPassword(String userEmail, String userPassword);

    boolean authorize(LoginForm loginForm);
}
