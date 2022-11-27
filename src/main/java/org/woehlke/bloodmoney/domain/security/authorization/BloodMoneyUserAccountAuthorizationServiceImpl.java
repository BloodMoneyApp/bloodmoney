package org.woehlke.bloodmoney.domain.security.authorization;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

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
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info(" confirmUserByLoginAndPassword: "+userEmail);
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        String confEmail = bloodMoneyProperties.getUserConfig().getUserEmail();
        String confPwd = bloodMoneyProperties.getUserConfig().getUserPassword();
        boolean confirmUserByLoginAndPassword = ( (userEmail.compareTo(confEmail)==0) && (userPassword.compareTo(confPwd)==0) );
        log.info(" confirmUserByLoginAndPassword: " + confirmUserByLoginAndPassword);
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return confirmUserByLoginAndPassword;
    }

    @Override
    public boolean authorize(LoginFormBean loginFormBean) {
        log.info("#####################################################################################");
        log.info("+++++ userEmail:  "+loginFormBean.getUserEmail());
        String encodedPassword = bloodMoneyProperties.getUserConfig().getUserPassword();
        log.info("+++++ encodedPassword:  ###"+encodedPassword+"###");
        CharSequence rawPassword = loginFormBean.getUserPassword();
        log.info("+++++ rawPassword:      ###"+rawPassword+"###  --- ###"+encoder.encode(rawPassword)+"###");
        boolean emailMatched = (loginFormBean.getUserEmail().compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0);
        log.info("+++++ emailMatched:  "+emailMatched);
        boolean pwMatched = (encoder.matches(rawPassword,encodedPassword));
        log.info("+++++ pwMatched:  "+pwMatched);
        boolean authorized = (emailMatched&&pwMatched);
        log.info("+++++ authorized:  "+authorized);
        log.info("#####################################################################################");
        return authorized;
    }
}
