package ru.posmanager;

import lombok.Getter;
import lombok.ToString;
import ru.posmanager.dto.user.UserDTO;

import java.io.Serial;

@ToString
public class AuthorizedUser extends org.springframework.security.core.userdetails.User {
    @Serial
    private static final long serialVersionUID = 1L;

    @Getter
    private final UserDTO userDTO;

    public AuthorizedUser(UserDTO dto) {
        super(dto.getEmail(), dto.getPassword(), dto.isEnabled(),
                true, true, true, dto.getRoles());
        userDTO = new UserDTO(dto);
        userDTO.setPassword(null);
    }
}
