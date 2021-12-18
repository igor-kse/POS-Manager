package ru.posmanager.web.controller.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.user.UserService;
import ru.posmanager.to.user.UserDTO;
import ru.posmanager.to.user.UserPreviewDTO;
import ru.posmanager.to.user.UserUpdateDTO;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = AdminRestController.USER_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRestController extends AbstractUserController {
    public static final String USER_REST_URL = "/api/admin/users";

    public AdminRestController(UserService service) {
        super(service);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<UserDTO> createWithLocation(@RequestBody UserUpdateDTO dto) {
        UserDTO created = super.create(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(USER_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("/{id}")
    public UserDTO get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<UserPreviewDTO> getAllUserPreviewDTO() {
        return super.getAllUserPreviewDTO();
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Map<String, Object> updates, @PathVariable int id) {
        super.update(updates, id,
                "firstName", "lastName", "middleName", "city", "email", "password", "departmentId", "enabled");
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}