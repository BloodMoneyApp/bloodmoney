package org.woehlke.bloodmoney.user.services.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.services.UserSessionService;
import org.woehlke.bloodmoney.frontend.model.UserSession;


@Log
@Service
public class UserSessionServiceImpl implements UserSessionService {

    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public UserSessionServiceImpl(BloodMoneyProperties bloodMoneyProperties) {
        this.bloodMoneyProperties = bloodMoneyProperties;
    }

    public Model handleUserSession(UserSession userSession, Model model){
        if(userSession==null){
            userSession = new UserSession();
            userSession.setDevTesting(bloodMoneyProperties.getDevTesting());
            model.addAttribute("userSession", userSession);
        }
        return model;
    }
}
