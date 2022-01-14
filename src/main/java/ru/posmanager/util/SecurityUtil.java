package ru.posmanager.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.posmanager.domain.user.User;
import ru.posmanager.dto.user.UserDTO;

@Slf4j
public class SecurityUtil {

    private static PasswordEncoder encoder;

    public static void initPasswordEncoder(PasswordEncoder encoder) {
        if (SecurityUtil.encoder != null) {
            throw new IllegalCallerException("The encoder is already initialized");
        }
        SecurityUtil.encoder = encoder;
    }

    public static String encodePassword(String password) {
        return encoder.encode(password);
    }

    public static void encodePassword(User user) {
        String encodedPassword = SecurityUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public static void encodePassword(UserDTO user) {
        String encodedPassword = SecurityUtil.encodePassword(user.getPassword());
        user.setPassword(encodedPassword);
    }

    public static boolean matches(String rawPassword, String encodedPassword) {
        log.debug("Matching passwords \nActual {} \nExpected {}", rawPassword, encodedPassword);
        return encoder.matches(rawPassword, encodedPassword);
    }
}
