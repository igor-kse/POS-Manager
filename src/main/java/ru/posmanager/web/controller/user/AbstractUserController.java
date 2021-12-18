package ru.posmanager.web.controller.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.user.UserService;
import ru.posmanager.to.user.UserDTO;
import ru.posmanager.to.user.UserPreviewDTO;
import ru.posmanager.to.user.UserUpdateDTO;
import ru.posmanager.util.CollectionUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractUserController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService service;

    public AbstractUserController(UserService service) {
        this.service = service;
    }

    public UserDTO create(UserUpdateDTO dto) {
        log.info("creating User from {}", dto);
        return service.create(dto);
    }

    public UserDTO get(int id) {
        log.info("getting User with id {}", id);
        return service.get(id);
    }

    public List<UserPreviewDTO> getAllUserPreviewDTO() {
        log.info("getting all UserPreviewDTO with");
        return service.getAllUserPreviewDTO();
    }

    public List<UserDTO> getAll() {
        log.info("getting all UserDTO");
        return service.getAll();
    }

    public void update(Map<String, Object> updates, int id, String... fields) {
        Map<String, Object> patch = new HashMap<>();
        CollectionUtil.addAllIfNotEmpty(updates, patch, fields);
        log.info("updating UserDTO {} with {}", id, patch);
        service.update(patch, id);
    }

    public void delete(int id) {
        log.info("deleting UserDTO with id {}", id);
        service.delete(id);
    }
}
