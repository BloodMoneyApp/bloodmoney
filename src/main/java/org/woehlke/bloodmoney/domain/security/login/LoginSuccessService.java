package org.woehlke.bloodmoney.domain.security.login;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.woehlke.bloodmoney.domain.security.vo.UserAccountVO;

public interface LoginSuccessService {

    String retrieveUsername();

    UserAccountVO retrieveCurrentUser() throws UsernameNotFoundException;

    void updateLastLoginTimestamp(UserAccountVO user);
}
