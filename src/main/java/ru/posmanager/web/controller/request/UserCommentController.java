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
    public static final String USER_COMMENT_REST_URL = "/api/comments/";
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final UserCommentService userCommentService;

    public UserCommentController(UserCommentService userCommentService) {
        this.userCommentService = userCommentService;
    }

    @PostMapping(value = "add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserCommentDTO> createWithLocation(@RequestBody UserCommentUpdateDTO userCommentUpdateDTO) {
        log.info("creating UserComment from {}", userCommentUpdateDTO);
        UserCommentDTO created = userCommentService.create(userCommentUpdateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(USER_COMMENT_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public UserCommentDTO get(@PathVariable("id") int userCommentId) {
        log.info("getting UserComment with id {}", userCommentId);
        return userCommentService.get(userCommentId);
    }

    @GetMapping
    public List<UserCommentDTO> getAll(@RequestParam("request") int requestId) {
        log.info("getting all UserComment");
        return userCommentService.getAll(requestId);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserCommentUpdateDTO userCommentUpdateDTO, @PathVariable("id") int userCommentId) {
        log.info("updating UserComment {} with {}", userCommentId, userCommentUpdateDTO);
        userCommentService.update(userCommentUpdateDTO, userCommentId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int userCommentId) {
        log.info("deleting UserComment {}", userCommentId);
        userCommentService.delete(userCommentId);
    }
}
