package org.woehlke.bloodmoney.domain.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.frontend.vo.LoginFormBean;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class AuthorizationServiceImpl implements AuthorizationService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public boolean confirmUserByLoginAndPassword(String userEmail, String userPassword) {
      String confEmail = bloodMoneyProperties.getUserConfig().getUserEmail();
      String confPwd   = bloodMoneyProperties.getUserConfig().getUserPassword();
      boolean confirmUserByLoginAndPassword = (
        (userEmail.compareTo(confEmail)==0)
          &&(userPassword.compareTo(confPwd)==0)
      );
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      log.info(" confEmail:    " + confEmail );
      log.info(" confPwd:      " + confPwd );
      log.info(" userEmail:    " + userEmail );
      log.info(" userPassword: " + userPassword );
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      log.info(" confirmUserByLoginAndPassword: " + confirmUserByLoginAndPassword);
      log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
      return confirmUserByLoginAndPassword;
    }

    @Override
    public boolean authorize(LoginFormBean loginFormBean) {
        String transientPassword = bloodMoneyProperties.getUserConfig().getUserPassword();
        CharSequence persistentPassword = loginFormBean.getUserPassword();
        boolean emailMatched = (loginFormBean.getUserEmail().compareTo(
          bloodMoneyProperties.getUserConfig().getUserEmail())==0
        );
        boolean pwMatched = (encoder.matches(persistentPassword,transientPassword));
        boolean authorized = (emailMatched&&pwMatched);
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info(" authorize ");
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        log.info("persistentPassword:           ###"+transientPassword+"###");
        log.info("persistentPassword endcoded:  ###"+encoder.encode(transientPassword)+"###");
        log.info("transientPassword:            ###"+persistentPassword+"###");
        log.info("transientPassword endcoded:   ###"+encoder.encode(persistentPassword)+"###");
        log.info("emailMatched:                 "+emailMatched);
        log.info("pwMatched:                    "+pwMatched);
        log.info("authorized:                   "+authorized);
        log.info("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return authorized;
    }
}
