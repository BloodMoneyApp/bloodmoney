package org.woehlke.bloodmoney.frontend.controller;

import org.springframework.ui.Model;
import org.woehlke.bloodmoney.frontend.model.UserSession;

public interface UserSessionControllerPart {
    Model handleUserSession(UserSession userSession, Model model);
}
