package ru.posmanager.web.controller.bankdevice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.bank.BankDeviceService;
import ru.posmanager.to.bank.BankDeviceDTO;
import ru.posmanager.to.bank.BankDevicePreviewDTO;
import ru.posmanager.to.bank.BankDeviceUpdateDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminBankDeviceRestController.BANK_DEVICE_ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminBankDeviceRestController extends AbstractBankDeviceController {
    public static final String BANK_DEVICE_ADMIN_REST_URL = "/api/admin/bankdevices/";

    public AdminBankDeviceRestController(BankDeviceService bankDeviceService) {
        super(bankDeviceService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<BankDeviceDTO> createWithLocation(@RequestBody BankDeviceUpdateDTO bankDeviceUpdateDTO) {
        BankDeviceDTO created = super.create(bankDeviceUpdateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(BANK_DEVICE_ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("{id}")
    public BankDeviceDTO get(@PathVariable("id") int bankDeviceId) {
        return super.get(bankDeviceId);
    }

    @Override
    @GetMapping
    public List<BankDeviceDTO> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("filter")
    public List<BankDevicePreviewDTO> getAllByTidAndAddress(@RequestParam(value = "tid", required = false) String tid,
                                                            @RequestParam(value = "address", required = false) String address) {
        return super.getAllByTidAndAddress(tid, address);
    }

    @Override
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BankDeviceUpdateDTO bankDeviceUpdateDTO, @PathVariable("id") int bankDeviceId) {
        super.update(bankDeviceUpdateDTO, bankDeviceId);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int bankDeviceId) {
        super.delete(bankDeviceId);
    }
}
