package ru.posmanager.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.AuthorizedUser;
import ru.posmanager.model.user.Role;
import ru.posmanager.service.user.UserService;
import ru.posmanager.web.security.UserRequest;
import ru.posmanager.web.security.UserResponse;

import javax.validation.Valid;

import static ru.posmanager.util.JsonWebTokenUtil.generateJsonWebToken;

@RestController
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;

    private final UserService userService;

    public AuthenticationRestController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@RequestBody @Valid UserRequest request) {
        String username = request.getUsername();
        String password = request.getPassword();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        AuthorizedUser user = userService.loadUserByUsername(username);
        String token = generateJsonWebToken(username, user.getUserDTO().getRoles().contains(Role.ADMIN));
        return ResponseEntity.ok(new UserResponse(user.getUserDTO(), token));
    }
}
