package org.woehlke.bloodmoney.domain.security.login;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.woehlke.bloodmoney.domain.security.vo.UserAccountBean;

public interface UserAccountLoginSuccessService {

    String retrieveUsername();

    UserAccountBean retrieveCurrentUser() throws UsernameNotFoundException;

    void updateLastLoginTimestamp(UserAccountBean user);
}
