package ru.posmanager.web.controller.affiliate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.bank.AffiliateService;
import ru.posmanager.to.bank.AffiliateDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminAffiliateRestController.AFFILIATE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminAffiliateRestController extends AbstractAffiliateController {
    public static final String AFFILIATE_REST_URL = "/api/admin/affiliates";
    private final Logger log = LoggerFactory.getLogger(getClass());

    public AdminAffiliateRestController(AffiliateService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AffiliateDTO> createWithLocation(@RequestBody AffiliateDTO to) {
        AffiliateDTO created = super.create(to);
        log.info("Created affiliate {}", created);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(AFFILIATE_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/{id}")
    public AffiliateDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<AffiliateDTO> getAll() {
        return super.getAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody AffiliateDTO to, @PathVariable int id) {
        super.update(to, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
