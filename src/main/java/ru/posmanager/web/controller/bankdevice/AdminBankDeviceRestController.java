package ru.posmanager.web.controller.bankdevice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.bank.BankDeviceService;
import ru.posmanager.to.bank.BankDeviceDTO;
import ru.posmanager.to.bank.BankDeviceUpdateDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminBankDeviceRestController.BANK_DEVICE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminBankDeviceRestController extends AbstractBankDeviceController {
    public static final String BANK_DEVICE_REST_URL = "/api/admin/bankdevices";

    public AdminBankDeviceRestController(BankDeviceService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankDeviceDTO> createWithLocation(@RequestBody BankDeviceUpdateDTO dto) {
        BankDeviceDTO created = super.create(dto);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(BANK_DEVICE_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("/{id}")
    public BankDeviceDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<BankDeviceDTO> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BankDeviceUpdateDTO dto, @PathVariable int id) {
        super.update(dto, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
