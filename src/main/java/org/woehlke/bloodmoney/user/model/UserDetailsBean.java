package org.woehlke.bloodmoney.user.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class UserDetailsBean implements UserDetails {

    private static final long serialVersionUID = 22L;

    private final String username;
    private final String password;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;


    public UserDetailsBean(String username, String password) {
        this.username = username;
        this.password = password;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorities;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDetailsBean)) return false;
        UserDetailsBean that = (UserDetailsBean) o;
        return isAccountNonExpired() == that.isAccountNonExpired() &&
            isAccountNonLocked() == that.isAccountNonLocked() &&
            isCredentialsNonExpired() == that.isCredentialsNonExpired() &&
            isEnabled() == that.isEnabled() &&
            Objects.equals(getUsername(), that.getUsername()) &&
            Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getPassword(), isAccountNonExpired(), isAccountNonLocked(), isCredentialsNonExpired(), isEnabled());
    }

    @Override
    public String toString() {
        return "UserDetailsBean{" +
            "username='" + username + '\'' +
            '}';
    }
}
