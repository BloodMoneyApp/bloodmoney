package org.woehlke.bloodmoney.domain.security.login;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAccountLoginSuccessService {

    String retrieveUsername();

    UserAccountVO retrieveCurrentUser() throws UsernameNotFoundException;

}
