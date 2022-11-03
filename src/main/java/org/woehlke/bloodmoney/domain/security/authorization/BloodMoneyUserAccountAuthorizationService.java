package org.woehlke.bloodmoney.domain.security.authorization;

import org.woehlke.bloodmoney.domain.security.vo.LoginFormBean;

public interface BloodMoneyUserAccountAuthorizationService {

    boolean confirmUserByLoginAndPassword(String userEmail, String userPassword);

    boolean authorize(LoginFormBean loginFormBean);
}
