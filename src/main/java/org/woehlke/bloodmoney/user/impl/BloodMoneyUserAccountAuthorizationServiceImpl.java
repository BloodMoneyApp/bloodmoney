package org.woehlke.bloodmoney.user.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.LoginFormBean;
import org.woehlke.bloodmoney.user.BloodMoneyUserAccountAuthorizationService;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BloodMoneyUserAccountAuthorizationServiceImpl implements BloodMoneyUserAccountAuthorizationService {

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
    public boolean authorize(LoginFormBean loginFormBean) {
        String encodedPassword = bloodMoneyProperties.getUserConfig().getUserPassword();
        log.info("encodedPassword:  ###"+encodedPassword+"###");
        CharSequence rawPassword = loginFormBean.getUserPassword();
        log.info("rawPassword:      ###"+rawPassword+"###  --- ###"+encoder.encode(rawPassword)+"###");
        boolean emailMatched = (loginFormBean.getUserEmail().compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0);
        log.info("emailMatched:  "+emailMatched);
        boolean pwMatched = (encoder.matches(rawPassword,encodedPassword));
        log.info("pwMatched:  "+pwMatched);
        boolean authorized = (emailMatched&&pwMatched);
        log.info("authorized:  "+authorized);
        return authorized;
    }
}
