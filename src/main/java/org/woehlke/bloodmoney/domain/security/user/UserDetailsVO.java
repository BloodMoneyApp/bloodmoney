package org.woehlke.bloodmoney.domain.security.user;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserDetailsVO implements UserDetails {

    private static final long serialVersionUID = 222L;

    private String username;
    private String password;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public static UserDetailsVO getInstance(String username, String password){
      UserDetailsVO o = new UserDetailsVO();
      o.username = username;
      o.password = password;
      o.accountNonExpired = true;
      o.accountNonLocked = true;
      o.credentialsNonExpired = true;
      o.enabled = true;
      SimpleGrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_USER");
      SimpleGrantedAuthority roleSuperuser = new SimpleGrantedAuthority("ROLE_SUPERUSER");
      o.authorities.add(roleUser);
      o.authorities.add(roleSuperuser);
      return o;
    }

}
