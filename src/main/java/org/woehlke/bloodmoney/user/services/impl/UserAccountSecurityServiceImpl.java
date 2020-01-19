package org.woehlke.bloodmoney.user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.ApplicationProperties;
import org.woehlke.bloodmoney.user.model.UserDetailsBean;
import org.woehlke.bloodmoney.user.services.UserAccountSecurityService;

@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserAccountSecurityServiceImpl implements UserAccountSecurityService {

    @Autowired
    private ApplicationProperties applicationProperties;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.compareTo(applicationProperties.getUserEmail())==0){
            return new UserDetailsBean(applicationProperties.getUserEmail(),applicationProperties.getUserPassword());
        } else {
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }
}
