package org.woehlke.bloodmoney.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserAccountLoginSuccessService {

    String retrieveUsername();

    UserAccountBean retrieveCurrentUser() throws UsernameNotFoundException;

    void updateLastLoginTimestamp(UserAccountBean user);
}
