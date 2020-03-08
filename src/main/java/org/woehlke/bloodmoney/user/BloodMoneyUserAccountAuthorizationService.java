package org.woehlke.bloodmoney.user;

public interface BloodMoneyUserAccountAuthorizationService {

    boolean confirmUserByLoginAndPassword(String userEmail, String userPassword);

    boolean authorize(LoginFormBean loginFormBean);
}
