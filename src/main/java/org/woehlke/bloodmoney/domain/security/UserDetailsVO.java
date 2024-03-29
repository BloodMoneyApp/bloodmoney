package org.woehlke.bloodmoney.domain.security;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class UserDetailsVO implements UserDetails {

  static final long serialVersionUID = 22L;

  private final String username;
  private final String password;
  private final boolean accountNonExpired;
  private final boolean accountNonLocked;
  private final boolean credentialsNonExpired;
  private final boolean enabled;
  private final List<GrantedAuthority> authorities;

  public UserDetailsVO(String username, String password) {
    this.username = username;
    this.password = password;
    this.accountNonExpired = true;
    this.accountNonLocked = true;
    this.credentialsNonExpired = true;
    this.enabled = true;
    List<GrantedAuthority> authoritiesVO = new ArrayList<GrantedAuthority>();
    authoritiesVO.add(new SimpleGrantedAuthority("ROLE_USER"));
    this.authorities = authoritiesVO;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return this.authorities;
  }

  @Override
  public String toString() {
    StringBuffer b = new StringBuffer();
    for (GrantedAuthority a : this.authorities) {
      b.append(a.getAuthority());
      b.append(", ");
    }
    return "UserDetailsVO {" +
      "username='" + username + '\'' +
      ", password='" + password + '\'' +
      ", accountNonExpired=" + accountNonExpired +
      ", accountNonLocked=" + accountNonLocked +
      ", credentialsNonExpired=" + credentialsNonExpired +
      ", enabled=" + enabled +
      ", authorities=" + b.toString() +
      '}';
  }
}
