package org.woehlke.bloodmoney.user.impl;

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
import org.woehlke.bloodmoney.user.UserAccount;
import org.woehlke.bloodmoney.user.UserAccountLoginSuccessService;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class UserAccountLoginSuccessServiceImpl implements UserAccountLoginSuccessService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    @Override
    public String retrieveUsername() {
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
    public UserAccount retrieveCurrentUser() throws UsernameNotFoundException {
        String username = this.retrieveUsername();
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
            return new UserAccount(
                bloodMoneyProperties.getUserConfig().getUserEmail(),
                bloodMoneyProperties.getUserConfig().getUserPassword(),
                bloodMoneyProperties.getUserConfig().getUserFullname()
            );
        } else {
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }

    @Override
    public void updateLastLoginTimestamp(UserAccount user) {
        //TODO:
    }
}
