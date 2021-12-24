package ru.posmanager.web.controller.bankdevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.bank.BankDeviceService;
import ru.posmanager.to.bank.BankDeviceDTO;
import ru.posmanager.to.bank.BankDevicePreviewDTO;
import ru.posmanager.to.bank.BankDeviceUpdateDTO;

import java.util.List;

public abstract class AbstractBankDeviceController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BankDeviceService bankDeviceService;

    public AbstractBankDeviceController(BankDeviceService bankDeviceService) {
        this.bankDeviceService = bankDeviceService;
    }

    public BankDeviceDTO create(BankDeviceUpdateDTO dto) {
        log.info("creating BankDeviceDTO from {}", dto);
        return bankDeviceService.create(dto);
    }

    public BankDeviceDTO get(int id) {
        log.info("getting BankDeviceDTO with id {}", id);
        return bankDeviceService.get(id);
    }

    public List<BankDeviceDTO> getAll() {
        log.info("getting all BankDeviceDTO");
        return bankDeviceService.getAll();
    }

    public List<BankDevicePreviewDTO> getAllByTidAndAddress(String tid, String address) {
        log.info("getting all BankDevicePreviewDTO filtered by tid={} and address={}", tid, address);
        return bankDeviceService.getAllByTidAndAddress(tid, address);
    }

    public void update(BankDeviceUpdateDTO dto, int id) {
        log.info("updating BankDeviceDTO {} with {}", id, dto);
        bankDeviceService.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting BankDeviceDTO with id {}", id);
        bankDeviceService.delete(id);
    }
}