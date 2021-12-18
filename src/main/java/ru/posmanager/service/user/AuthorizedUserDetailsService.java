package ru.posmanager.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.posmanager.AuthorizedUser;

public interface AuthorizedUserDetailsService extends UserDetailsService {

    AuthorizedUser loadUserByUsername(String username) throws UsernameNotFoundException;
}
