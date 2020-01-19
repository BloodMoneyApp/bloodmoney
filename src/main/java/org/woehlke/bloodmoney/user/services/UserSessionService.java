package org.woehlke.bloodmoney.user.services;

import org.springframework.ui.Model;
import org.woehlke.bloodmoney.frontend.model.UserSession;

public interface UserSessionService {
    Model handleUserSession(UserSession userSession, Model model);
}
