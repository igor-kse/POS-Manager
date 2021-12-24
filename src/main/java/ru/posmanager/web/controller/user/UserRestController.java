package ru.posmanager.web.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.posmanager.service.user.UserService;
import ru.posmanager.to.user.UserDTO;
import ru.posmanager.to.user.UserPreviewDTO;
import ru.posmanager.web.security.AuthorizedUserExtractor;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = UserRestController.USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserRestController extends AbstractUserController {
    public static final String USER_REST_URL = "/api/users/";

    private final AuthorizedUserExtractor authorizedUserExtractor;

    public UserRestController(UserService userService, AuthorizedUserExtractor authorizedUserExtractor) {
        super(userService);
        this.authorizedUserExtractor = authorizedUserExtractor;
    }

    @GetMapping("profile")
    public UserDTO profile() {
        return authorizedUserExtractor.get().getUserDTO();
    }

    @GetMapping("{id}")
    public UserDTO get(@PathVariable("id") int userId) {
        return super.get(userId);
    }

    @Override
    @GetMapping
    public List<UserPreviewDTO> getAllUserPreviewDTO() {
        return super.getAllUserPreviewDTO();
    }

    @Override
    @GetMapping("filter")
    public List<UserPreviewDTO> getAllFilteredUserPreviewDTO(
            @RequestParam(value = "last_name", required = false) String lastName,
            @RequestParam(value = "first_name", required = false) String firstName,
            @RequestParam(value = "middle_name", required = false) String middleName) {
        return super.getAllFilteredUserPreviewDTO(lastName, firstName, middleName);
    }

    @PatchMapping(value = "profile", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Map<String, Object> updates) {
        int userId = authorizedUserExtractor.authorizedUserId();
        super.update(updates, userId,"password");
    }
}