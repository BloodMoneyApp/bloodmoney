package org.woehlke.bloodmoney.domain.meso.session;

import org.springframework.ui.Model;

public interface UserSessionService {
    Model handleUserSession(UserSessionVO userSessionVO, Model model);
}
