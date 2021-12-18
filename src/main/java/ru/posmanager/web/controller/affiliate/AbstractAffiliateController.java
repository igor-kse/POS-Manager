package ru.posmanager.web.controller.affiliate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.bank.AffiliateService;
import ru.posmanager.to.bank.AffiliateDTO;

import java.util.List;

public abstract class AbstractAffiliateController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final AffiliateService service;

    public AbstractAffiliateController(AffiliateService service) {
        this.service = service;
    }

    public AffiliateDTO create(AffiliateDTO to) {
        log.info("creating AffiliateDTO from {}", to);
        return service.create(to);
    }

    public AffiliateDTO get(int id) {
        log.info("getting AffiliateDTO with id {}", id);
        return service.get(id);
    }

    public List<AffiliateDTO> getAll() {
        log.info("getting all AffiliateDTO");
        return service.getAll();
    }

    public void update(AffiliateDTO to, int id) {
        log.info("updating AffiliateDTO {} with {}", id, to);
        service.update(to, id);
    }

    public void delete(int id) {
        log.info("deleting AffiliateDTO with id {}", id);
        service.delete(id);
    }
}