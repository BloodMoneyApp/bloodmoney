package org.woehlke.bloodmoney.user.services.impl;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;
import org.woehlke.bloodmoney.user.model.UserDetailsBean;
import org.woehlke.bloodmoney.user.services.UserAccountSecurityService;

@Log
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
