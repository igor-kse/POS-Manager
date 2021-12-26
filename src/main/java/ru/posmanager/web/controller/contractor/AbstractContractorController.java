package ru.posmanager.web.controller.contractor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.posmanager.service.bank.ContractorService;
import ru.posmanager.to.bank.ContractorDTO;
import ru.posmanager.web.validator.UniqueValidator;

import java.util.List;

public abstract class AbstractContractorController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ContractorService service;
    private final UniqueValidator uniqueValidator;

    public AbstractContractorController(ContractorService contractorService, UniqueValidator uniqueValidator) {
        this.service = contractorService;
        this.uniqueValidator = uniqueValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder webDataBinder) {
        webDataBinder.addValidators(uniqueValidator);
    }

    public ContractorDTO create(ContractorDTO dto) {
        log.info("creating ContractorDTO from {}", dto);
        return service.create(dto);
    }

    public ContractorDTO get(int id) {
        log.info("getting ContractorDTO with id {}", id);
        return service.get(id);
    }

    public List<ContractorDTO> getAll() {
        log.info("getting all ContractorDTO");
        return service.getAll();
    }

    public void update(ContractorDTO dto, int id) {
        log.info("updating ContractorDTO {} with {}", id, dto);
        service.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting ContractorDTO with id {}", id);
        service.delete(id);
    }
}