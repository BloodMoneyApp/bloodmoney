package org.woehlke.bloodmoney.user.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.model.LoginForm;
import org.woehlke.bloodmoney.user.services.UserAccountAccessService;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserAccountAccessServiceImpl implements UserAccountAccessService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountAccessServiceImpl.class);

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    private PasswordEncoder encoder;


    @Override
    public boolean confirmUserByLoginAndPassword(String userEmail, String userPassword) {
        return ((userEmail.compareTo(bloodMoneyProperties.getUserEmail())==0)&&(userPassword.compareTo(bloodMoneyProperties.getUserPassword())==0));
    }

    @Override
    public boolean authorize(LoginForm loginForm) {
        String encodedPassword = bloodMoneyProperties.getUserPassword();
        LOGGER.warn("encodedPassword:  ###"+encodedPassword+"###");
        CharSequence rawPassword = loginForm.getUserPassword();
        LOGGER.warn("rawPassword:      ###"+rawPassword+"###  --- ###"+encoder.encode(rawPassword)+"###");
        boolean emailMatched = (loginForm.getUserEmail().compareTo(bloodMoneyProperties.getUserEmail())==0);
        LOGGER.warn("emailMatched:  "+emailMatched);
        boolean pwMatched = (encoder.matches(rawPassword,encodedPassword));
        LOGGER.warn("pwMatched:  "+pwMatched);
        return (emailMatched&&pwMatched);
    }
}
