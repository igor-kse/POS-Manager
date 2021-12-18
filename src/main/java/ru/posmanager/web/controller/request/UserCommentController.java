package ru.posmanager.web.controller.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.request.UserCommentService;
import ru.posmanager.to.request.UserCommentDTO;
import ru.posmanager.to.request.UserCommentUpdateDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = UserCommentController.USER_COMMENT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class UserCommentController {
    public static final String USER_COMMENT_REST_URL = "/api/comments";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserCommentService service;

    public UserCommentController(UserCommentService service) {
        this.service = service;
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCommentDTO> createWithLocation(@RequestBody UserCommentUpdateDTO commentCreateDTO) {
        log.info("creating UserComment from {}", commentCreateDTO);
        UserCommentDTO created = service.create(commentCreateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(USER_COMMENT_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/{id}")
    public UserCommentDTO get(@PathVariable("id") int id) {
        log.info("getting UserComment with id {}", id);
        return service.get(id);
    }

    @GetMapping
    public List<UserCommentDTO> getAll(@RequestParam("request") int requestId) {
        log.info("getting all UserComment");
        return service.getAll(requestId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserCommentUpdateDTO dto, @PathVariable("id") int id) {
        log.info("updating UserComment {} with {}", id, dto);
        service.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        log.info("deleting UserComment {}", id);
        service.delete(id);
    }
}
