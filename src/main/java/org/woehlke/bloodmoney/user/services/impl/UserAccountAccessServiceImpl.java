package org.woehlke.bloodmoney.user.services.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.application.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.model.LoginForm;
import org.woehlke.bloodmoney.user.services.UserAccountAccessService;

@Log
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserAccountAccessServiceImpl implements UserAccountAccessService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public boolean confirmUserByLoginAndPassword(String userEmail, String userPassword) {
        return (
            (userEmail.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0)
            &&(userPassword.compareTo(bloodMoneyProperties.getUserConfig().getUserPassword())==0)
        );
    }

    @Override
    public boolean authorize(LoginForm loginForm) {
        String encodedPassword = bloodMoneyProperties.getUserConfig().getUserPassword();
        log.info("encodedPassword:  ###"+encodedPassword+"###");
        CharSequence rawPassword = loginForm.getUserPassword();
        log.info("rawPassword:      ###"+rawPassword+"###  --- ###"+encoder.encode(rawPassword)+"###");
        boolean emailMatched = (loginForm.getUserEmail().compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0);
        log.info("emailMatched:  "+emailMatched);
        boolean pwMatched = (encoder.matches(rawPassword,encodedPassword));
        log.info("pwMatched:  "+pwMatched);
        boolean authorized = (emailMatched&&pwMatched);
        log.info("authorized:  "+authorized);
        return authorized;
    }
}
