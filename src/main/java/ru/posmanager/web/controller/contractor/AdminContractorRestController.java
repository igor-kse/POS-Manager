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
@RequestMapping(value = AdminContractorRestController.CONTRACTOR_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminContractorRestController extends AbstractContractorController {
    public static final String CONTRACTOR_REST_URL = "/api/admin/contractors";

    public AdminContractorRestController(ContractorService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContractorDTO> createWithLocation(@RequestBody ContractorDTO contractorDTO) {
        ContractorDTO created = super.create(contractorDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(CONTRACTOR_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("/{id}")
    public ContractorDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<ContractorDTO> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody ContractorDTO to, @PathVariable int id) {
        super.update(to, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
