package org.woehlke.bloodmoney.domain.security;

import org.woehlke.bloodmoney.frontend.vo.LoginFormBean;

public interface BloodMoneyAuthorizationService {

    boolean confirmUserByLoginAndPassword(String userEmail, String userPassword);

    boolean authorize(LoginFormBean loginFormBean);
}
