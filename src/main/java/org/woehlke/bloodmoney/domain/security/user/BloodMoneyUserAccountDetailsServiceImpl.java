package org.woehlke.bloodmoney.domain.security.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.domain.security.vo.UserAccountDetailsBean;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BloodMoneyUserAccountDetailsServiceImpl implements BloodMoneyUserAccountDetailsService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
            return new UserAccountDetailsBean(bloodMoneyProperties.getUserConfig().getUserEmail(), bloodMoneyProperties.getUserConfig().getUserPassword());
        } else {
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }
}
