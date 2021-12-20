package ru.posmanager.web.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.posmanager.AuthorizedUser;
import ru.posmanager.service.user.AuthorizedUserDetailsService;

import java.util.Objects;

@Component
public class AuthorizedUserExtractor {

    private final AuthorizedUserDetailsService userDetailsService;

    public AuthorizedUserExtractor(AuthorizedUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public AuthorizedUser safeGet() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return (principal instanceof String username) ? userDetailsService.loadUserByUsername(username) : null;
    }

    public AuthorizedUser get() {
        String failMessage = "No authorized user found or this kind of authentication is not supported";
        return Objects.requireNonNull(safeGet(), failMessage);
    }

    public int authorizedUserId() {
        return get().getUserDTO().id();
    }
}