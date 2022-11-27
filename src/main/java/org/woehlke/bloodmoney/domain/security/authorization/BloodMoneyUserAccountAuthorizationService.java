package org.woehlke.bloodmoney.domain.security.authorization;

public interface BloodMoneyUserAccountAuthorizationService {

    boolean confirmUserByLoginAndPassword(String userEmail, String userPassword);

    boolean authorize(LoginFormBean loginFormBean);
}
