package ru.posmanager.web.controller.request;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.request.RequestService;
import ru.posmanager.to.request.RequestDTO;
import ru.posmanager.to.request.RequestPreviewDTO;
import ru.posmanager.to.request.RequestUpdateDTO;
import ru.posmanager.web.security.AuthorizedUserExtractor;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRequestRestController.ADMIN_REQUEST_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRequestRestController extends AbstractRequestController {
    public static final String ADMIN_REQUEST_REST_URL = "/api/admin/requests";

    private final AuthorizedUserExtractor authorizedUserExtractor;

    public AdminRequestRestController(RequestService service, AuthorizedUserExtractor authorizedUserExtractor) {
        super(service);
        this.authorizedUserExtractor = authorizedUserExtractor;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestDTO> createWithLocation(@RequestBody RequestUpdateDTO dto) {
        int authorId = authorizedUserExtractor.authorizedUserId();
        dto.setAuthorId(authorId);
        RequestDTO created = super.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(ADMIN_REQUEST_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/{id}")
    public RequestDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<RequestPreviewDTO> getAllPreview() {
        return super.getAllPreview();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody RequestUpdateDTO dto, @PathVariable int id) {
        super.update(dto, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
