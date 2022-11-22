package org.woehlke.bloodmoney.domain.session;

import org.springframework.ui.Model;

public interface UserSessionService {
    Model handleUserSession(UserSessionBean userSessionBean, Model model);
}
