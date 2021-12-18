package ru.posmanager.config;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.posmanager.service.user.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static java.util.Objects.isNull;
import static ru.posmanager.util.JsonWebTokenUtil.getSubject;
import static ru.posmanager.util.JsonWebTokenUtil.isValid;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final UserService userService;

    public SecurityFilter(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String username;
        String token = request.getHeader("Authorization");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!isNull(token) && !isNull(username = getSubject(token)) && isNull(auth)) {
            UserDetails user = userService.loadUserByUsername(username);
            if (isValid(token) && !isNull(user)) {
                setAuthentication(user, request);
            }
        }
        filterChain.doFilter(request, response);
    }

    private void setAuthentication(UserDetails user, HttpServletRequest request) {
        var authToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);
    }
}