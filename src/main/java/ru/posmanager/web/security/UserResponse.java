package ru.posmanager.web.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.user.UserDTO;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private UserDTO userTo;
    private String token;
}
