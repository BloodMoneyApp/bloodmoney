package org.woehlke.bloodmoney.domain.security.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

@Slf4j
@Service("bloodMoneyUserAccountDetailsService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BloodMoneyUserAccountDetailsServiceImpl implements BloodMoneyUserAccountDetailsService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("----------------------------------------------------------------------");
        log.info(" UserDetailsService: load User By Username: "+username);
        log.info("----------------------------------------------------------------------");
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
            UserAccountDetailsBean o = new UserAccountDetailsBean(
              bloodMoneyProperties.getUserConfig().getUserEmail(),
              bloodMoneyProperties.getUserConfig().getUserPassword()
            );
            log.info("UserAccountDetailsBean : "+o.toString());
            log.info("----------------------------------------------------------------------");
            return o;
        } else {
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }
}
