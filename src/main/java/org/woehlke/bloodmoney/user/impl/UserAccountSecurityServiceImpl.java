package org.woehlke.bloodmoney.user.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.application.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.UserDetailsBean;
import org.woehlke.bloodmoney.user.UserAccountSecurityService;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserAccountSecurityServiceImpl implements UserAccountSecurityService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
            return new UserDetailsBean(bloodMoneyProperties.getUserConfig().getUserEmail(), bloodMoneyProperties.getUserConfig().getUserPassword());
        } else {
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }
}
