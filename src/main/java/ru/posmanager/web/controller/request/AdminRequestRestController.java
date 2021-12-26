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

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminRequestRestController.REQUEST_ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminRequestRestController extends AbstractRequestController {
    public static final String REQUEST_ADMIN_REST_URL = "/api/admin/requests/";

    private final AuthorizedUserExtractor authorizedUserExtractor;

    public AdminRequestRestController(RequestService requestService, AuthorizedUserExtractor authorizedUserExtractor) {
        super(requestService);
        this.authorizedUserExtractor = authorizedUserExtractor;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RequestDTO> createWithLocation(@RequestBody @Valid RequestUpdateDTO requestUpdateDTO) {
        int authorId = authorizedUserExtractor.authorizedUserId();
        requestUpdateDTO.setAuthorId(authorId);
        RequestDTO created = super.create(requestUpdateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REQUEST_ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public RequestDTO get(@PathVariable("id") int requestId) {
        return super.get(requestId);
    }

    @GetMapping
    public List<RequestPreviewDTO> getAllPreview() {
        return super.getAllPreview();
    }

    @GetMapping("filter")
    public List<RequestPreviewDTO> getAllFiltered(@RequestParam(value = "title", required = false) String title,
                                                  @RequestParam(value = "status", required = false) String requestStatus) {
        return super.getAllFiltered(title, requestStatus);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid RequestUpdateDTO requestUpdateDTO, @PathVariable("id") int requestId) {
        super.update(requestUpdateDTO, requestId);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int requestId) {
        super.delete(requestId);
    }
}