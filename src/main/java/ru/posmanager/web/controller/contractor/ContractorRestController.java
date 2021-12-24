package ru.posmanager.web.controller.contractor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.service.bank.ContractorService;
import ru.posmanager.to.bank.ContractorDTO;

import java.util.List;

@RestController
@RequestMapping(value = ContractorRestController.CONTRACTOR_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ContractorRestController extends AbstractContractorController {
    public static final String CONTRACTOR_REST_URL = "/api/contractors/";

    public ContractorRestController(ContractorService contractorService) {
        super(contractorService);
    }

    @Override
    @GetMapping("{id}")
    public ContractorDTO get(@PathVariable("id") int contractorId) {
        return super.get(contractorId);
    }

    @Override
    @GetMapping
    public List<ContractorDTO> getAll() {
        return super.getAll();
    }
}
