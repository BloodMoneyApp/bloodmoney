package org.woehlke.bloodmoney.domain.security.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.woehlke.bloodmoney.config.BloodMoneyProperties;

@Slf4j
@Service("bloodMoneyUserDetailsServiceService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public class BloodMoneyUserDetailsServiceServiceImpl implements BloodMoneyUserDetailsServiceService {

    @Autowired
    private BloodMoneyProperties bloodMoneyProperties;

    //@Autowired
    //private PasswordEncoder encoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("----------------------------------------------------------------------");
        log.info(" UserDetailsService: load User By Username: "+username);
        log.info("----------------------------------------------------------------------");
        if(username.compareTo(bloodMoneyProperties.getUserConfig().getUserEmail())==0){
          UserDetails user = User
            .withUsername(bloodMoneyProperties.getUserConfig().getUserEmail())
            .password(bloodMoneyProperties.getUserConfig().getUserPassword())
            .roles("USER","ROOT")
            .build();
            log.info(" UserDetails user : "+user.toString());
            //log.info(" UserDetails user : "+user.getPassword());
            //log.info(encoder.encode("Recoil89"));
            log.info("----------------------------------------------------------------------");
            return user;
        } else {
          log.info("----------------------------------------------------------------------");
          log.info(" Usernam unknown: "+username);
          log.info("----------------------------------------------------------------------");
            throw new UsernameNotFoundException("Usernam unknown: "+username);
        }
    }
}
