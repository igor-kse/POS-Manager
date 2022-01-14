package ru.posmanager.web.controller.bankdevice;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.posmanager.service.bank.BankDeviceService;
import ru.posmanager.dto.bank.BankDeviceDTO;
import ru.posmanager.dto.bank.BankDevicePreviewDTO;

import java.util.List;

@RestController
@RequestMapping(value = BankDeviceRestController.BANK_DEVICE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class BankDeviceRestController extends AbstractBankDeviceController {
    public static final String BANK_DEVICE_REST_URL = "/api/bankdevices/";

    public BankDeviceRestController(BankDeviceService bankDeviceService) {
        super(bankDeviceService);
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
}
