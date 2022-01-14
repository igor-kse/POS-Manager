package ru.posmanager.web.controller.affiliate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.bank.AffiliateService;
import ru.posmanager.dto.bank.AffiliateDTO;
import ru.posmanager.web.validator.UniqueValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminAffiliateRestController.AFFILIATE_ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminAffiliateRestController extends AbstractAffiliateController {
    public static final String AFFILIATE_ADMIN_REST_URL = "/api/admin/affiliates/";
    private final Logger log = LoggerFactory.getLogger(getClass());

    public AdminAffiliateRestController(AffiliateService affiliateService, UniqueValidator uniqueValidator) {
        super(affiliateService, uniqueValidator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AffiliateDTO> createWithLocation(@RequestBody @Valid AffiliateDTO affiliateDTO) {
        AffiliateDTO created = super.create(affiliateDTO);
        log.info("Created affiliate {}", created);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(AFFILIATE_ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("{id}")
    public AffiliateDTO get(@PathVariable("id") int affiliateId) {
        return super.get(affiliateId);
    }

    @GetMapping
    public List<AffiliateDTO> getAll() {
        return super.getAll();
    }

    @PutMapping("{affiliateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid AffiliateDTO to, @PathVariable int affiliateId) {
        super.update(to, affiliateId);
    }

    @DeleteMapping("{affiliateId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int affiliateId) {
        super.delete(affiliateId);
    }
}
