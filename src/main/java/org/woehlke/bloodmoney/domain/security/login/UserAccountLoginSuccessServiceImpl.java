package org.woehlke.bloodmoney.domain.security.login;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserAccountLoginSuccessServiceImpl implements UserAccountLoginSuccessService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Override
      public String retrieveUsername() {
        log.info("-------------------------------------------------------------------------------------");
        log.info(" retrieveUsername ");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) return " ";
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            UserDetails ud = ((UserDetails) principal);
            log.info(" principal instanceof UserDetails ");
            log.info(ud.toString());
            log.info("-------------------------------------------------------------------------------------");
            return ud.getUsername();
        } else {
            log.info(" principal is NOT instanceof UserDetails, username: ");
            String username = principal.toString();
            log.info(username);
            log.info("-------------------------------------------------------------------------------------");
            return username;
        }
    }

    @Override
      public UserAccountBean retrieveCurrentUser() throws UsernameNotFoundException {
        log.info("-------------------------------------------------------------------------------------");
        log.info(" retrieve Current User ");
        log.info("-------------------------------------------------------------------------------------");
        String username = this.retrieveUsername();
        log.info(" retrieved username: "+username);
        log.info("-------------------------------------------------------------------------------------");
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
          UserAccountBean ub = new UserAccountBean(
                bloodMoneyProperties.getUserConfig().getUserEmail(),
                bloodMoneyProperties.getUserConfig().getUserPassword(),
                bloodMoneyProperties.getUserConfig().getUserFullname()
            );
          UserDetails u = User.withUserDetails((UserDetails)ub).build();
          log.info(" UserAccountBean u = "+u.toString());
          log.info("-------------------------------------------------------------------------------------");
          return ub;
        } else {
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }

}
