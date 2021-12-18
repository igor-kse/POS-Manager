package ru.posmanager.web.controller.contractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.bank.ContractorService;
import ru.posmanager.to.bank.ContractorDTO;

import java.util.List;

public abstract class AbstractContractorController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ContractorService service;

    public AbstractContractorController(ContractorService service) {
        this.service = service;
    }

    public ContractorDTO create(ContractorDTO to) {
        log.info("creating ContractorDTO from {}", to);
        return service.create(to);
    }

    public ContractorDTO get(int id) {
        log.info("getting ContractorDTO with id {}", id);
        return service.get(id);
    }

    public List<ContractorDTO> getAll() {
        log.info("getting all ContractorDTO");
        return service.getAll();
    }

    public void update(ContractorDTO to, int id) {
        log.info("updating ContractorDTO {} with {}", id, to);
        service.update(to, id);
    }

    public void delete(int id) {
        log.info("deleting ContractorDTO with id {}", id);
        service.delete(id);
    }
}