package org.woehlke.bloodmoney.domain.security.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

@Slf4j
@Service("bloodMoneyUserDetailsService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BloodMoneyUserDetailsServiceImpl implements BloodMoneyUserDetailsService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("-------------------------------------------------------------------------------------");
        log.info(" retrieveCurrentUser ");
        log.info("-------------------------------------------------------------------------------------");
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
          UserAccountDetailsBean ub = new UserAccountDetailsBean(
              bloodMoneyProperties.getUserConfig().getUserEmail(),
              bloodMoneyProperties.getUserConfig().getUserPassword()
            );
          UserDetails u = User.withUserDetails(ub).build();
          log.info(" UserAccountDetailsBean:  ");
          log.info(ub.toString());
          log.info("-------------------------------------------------------------------------------------");
          return ub;
        } else {
            log.info(" throw new UsernameNotFoundException(Usernam unknown: "+username+");");
            log.info("-------------------------------------------------------------------------------------");
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }
}
