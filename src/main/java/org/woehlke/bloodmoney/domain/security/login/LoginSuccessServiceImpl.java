package org.woehlke.bloodmoney.domain.security.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.domain.security.vo.UserAccountVO;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class LoginSuccessServiceImpl implements LoginSuccessService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Override
    public String retrieveUsername() {
        log.info("----------------------------------------------------------------------");
        log.info(" retrieve Username ");
        log.info("----------------------------------------------------------------------");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) return " ";
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        } else {
            return principal.toString();
        }
    }

    @Override
    public UserAccountVO retrieveCurrentUser() throws UsernameNotFoundException {
        log.info("----------------------------------------------------------------------");
        log.info(" retrieve Current User");
        log.info("----------------------------------------------------------------------");
        String username = this.retrieveUsername();
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
            return new UserAccountVO(
                bloodMoneyProperties.getUserConfig().getUserEmail(),
                bloodMoneyProperties.getUserConfig().getUserPassword(),
                bloodMoneyProperties.getUserConfig().getUserFullname()
            );
        } else {
            log.info("----------------------------------------------------------------------");
            log.info(" Usernam unknown: "+username);
            log.info("----------------------------------------------------------------------");
            throw new UsernameNotFoundException(" Usernam unknown: "+username);
        }
    }

    @Override
    public void updateLastLoginTimestamp(UserAccountVO user) {
      log.info("----------------------------------------------------------------------");
      log.info(" update Last Login Timestamp");
      log.info("----------------------------------------------------------------------");
        //TODO:
    }
}
