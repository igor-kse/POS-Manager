package ru.posmanager.web.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.user.UserService;
import ru.posmanager.dto.user.UserDTO;
import ru.posmanager.dto.user.UserPreviewDTO;
import ru.posmanager.dto.user.UserUpdateDTO;
import ru.posmanager.web.validator.UniqueValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = AdminRestController.ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractUserController {
    public static final String ADMIN_REST_URL = "/api/admin/users/";

    public AdminRestController(UserService userService, UniqueValidator uniqueValidator) {
        super(userService, uniqueValidator);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDTO> createWithLocation(@RequestBody @Valid UserUpdateDTO userUpdateDTO) {
        UserDTO created = super.create(userUpdateDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
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

    @PatchMapping(value = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Map<String, Object> updates, @PathVariable("id") int userId) {
        super.update(updates, userId,
                "firstName", "lastName", "middleName", "city", "email", "password", "departmentId", "enabled");
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int userId) {
        super.delete(userId);
    }
}