package org.woehlke.bloodmoney.domain.security.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class UserAccountDetailsBean implements UserDetails {

    private static final long serialVersionUID = 222L;

    private final String username;
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;
    private List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public UserAccountDetailsBean(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
        SimpleGrantedAuthority roleUser = new SimpleGrantedAuthority("ROLE_USER");
        SimpleGrantedAuthority roleSuperuser = new SimpleGrantedAuthority("ROLE_SUPERUSER");
        authorities.add(roleUser);
        authorities.add(roleSuperuser);
    }

}
