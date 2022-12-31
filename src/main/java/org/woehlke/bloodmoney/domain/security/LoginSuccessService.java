package org.woehlke.bloodmoney.domain.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface LoginSuccessService {

  String retrieveUsername();

  UserDetails retrieveCurrentUser() throws UsernameNotFoundException;

}
