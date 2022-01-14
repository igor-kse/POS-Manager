package ru.posmanager.web.controller.affiliate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.posmanager.service.bank.AffiliateService;
import ru.posmanager.dto.bank.AffiliateDTO;
import ru.posmanager.web.validator.UniqueValidator;

import java.util.List;

public abstract class AbstractAffiliateController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AffiliateService affiliateService;
    private final UniqueValidator uniqueValidator;

    public AbstractAffiliateController(AffiliateService affiliateService, UniqueValidator uniqueValidator) {
        this.affiliateService = affiliateService;
        this.uniqueValidator = uniqueValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(uniqueValidator);
    }

    public AffiliateDTO create(AffiliateDTO dto) {
        log.info("creating AffiliateDTO from {}", dto);
        return affiliateService.create(dto);
    }

    public AffiliateDTO get(int id) {
        log.info("getting AffiliateDTO with id {}", id);
        return affiliateService.get(id);
    }

    public List<AffiliateDTO> getAll() {
        log.info("getting all AffiliateDTO");
        return affiliateService.getAll();
    }

    public void update(AffiliateDTO to, int id) {
        log.info("updating AffiliateDTO {} with {}", id, to);
        affiliateService.update(to, id);
    }

    public void delete(int id) {
        log.info("deleting AffiliateDTO with id {}", id);
        affiliateService.delete(id);
    }
}