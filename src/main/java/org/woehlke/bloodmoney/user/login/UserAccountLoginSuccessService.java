package org.woehlke.bloodmoney.user.login;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.woehlke.bloodmoney.user.account.UserAccount;

public interface UserAccountLoginSuccessService {

    String retrieveUsername();

    UserAccount retrieveCurrentUser() throws UsernameNotFoundException;

    void updateLastLoginTimestamp(UserAccount user);
}
