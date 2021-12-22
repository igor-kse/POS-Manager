package ru.posmanager.web.controller.bankdevice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.posmanager.service.bank.BankDeviceService;
import ru.posmanager.to.bank.BankDeviceDTO;
import ru.posmanager.to.bank.BankDevicePreviewDTO;

import java.util.List;

@RestController
@RequestMapping(value = BankDeviceRestController.BANK_DEVICE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class BankDeviceRestController extends AbstractBankDeviceController {
    public static final String BANK_DEVICE_REST_URL = "/api/bankdevices";

    public BankDeviceRestController(BankDeviceService service) {
        super(service);
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
    @GetMapping("/filter")
    public List<BankDevicePreviewDTO> getAllByTidAndAddress(@RequestParam(value = "tid", required = false) String tid,
                                                            @RequestParam(value = "address", required = false) String address) {
        return super.getAllByTidAndAddress(tid, address);
    }
}
