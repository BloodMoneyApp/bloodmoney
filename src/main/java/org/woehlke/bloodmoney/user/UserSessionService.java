package org.woehlke.bloodmoney.user;

import org.springframework.ui.Model;

public interface UserSessionService {
    Model handleUserSession(UserSession userSession, Model model);
}
