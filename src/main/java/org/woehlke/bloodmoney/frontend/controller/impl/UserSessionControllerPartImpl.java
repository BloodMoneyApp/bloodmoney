package org.woehlke.bloodmoney.frontend.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.frontend.controller.UserSessionControllerPart;
import org.woehlke.bloodmoney.frontend.model.UserSession;

@Service
public class UserSessionControllerPartImpl implements UserSessionControllerPart {

    private final BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    public UserSessionControllerPartImpl(BloodMoneyProperties bloodMoneyProperties) {
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
