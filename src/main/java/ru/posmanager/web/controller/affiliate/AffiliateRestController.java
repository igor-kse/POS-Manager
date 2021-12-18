package ru.posmanager.web.controller.affiliate;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.service.bank.AffiliateService;
import ru.posmanager.to.bank.AffiliateDTO;

import java.util.List;

@RestController
@RequestMapping(value = AffiliateRestController.AFFILIATE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AffiliateRestController extends AbstractAffiliateController {
    public static final String AFFILIATE_REST_URL = "/api/affiliates";

    public AffiliateRestController(AffiliateService service) {
        super(service);
    }

    @GetMapping("/{id}")
    public AffiliateDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @GetMapping
    public List<AffiliateDTO> getAll() {
        return super.getAll();
    }
}
