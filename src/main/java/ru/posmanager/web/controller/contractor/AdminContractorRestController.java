package ru.posmanager.web.controller.contractor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.bank.ContractorService;
import ru.posmanager.to.bank.ContractorDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminContractorRestController.CONTRACTOR_ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminContractorRestController extends AbstractContractorController {
    public static final String CONTRACTOR_ADMIN_REST_URL = "/api/admin/contractors/";

    public AdminContractorRestController(ContractorService contractorService) {
        super(contractorService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContractorDTO> createWithLocation(@RequestBody ContractorDTO contractorDTO) {
        ContractorDTO created = super.create(contractorDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTRACTOR_ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
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

    @Override
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody ContractorDTO contractorDTO, @PathVariable("id") int contractorId) {
        super.update(contractorDTO, contractorId);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int contractorId) {
        super.delete(contractorId);
    }
}
