package org.woehlke.bloodmoney.user.services;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.woehlke.bloodmoney.oodm.model.UserAccount;

public interface UserAccountLoginSuccessService {

    String retrieveUsername();

    UserAccount retrieveCurrentUser() throws UsernameNotFoundException;

    void updateLastLoginTimestamp(UserAccount user);
}
