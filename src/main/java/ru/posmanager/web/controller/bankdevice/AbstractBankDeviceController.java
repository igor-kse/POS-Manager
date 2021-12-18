package ru.posmanager.web.controller.bankdevice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.bank.BankDeviceService;
import ru.posmanager.to.bank.BankDeviceDTO;
import ru.posmanager.to.bank.BankDeviceUpdateDTO;

import java.util.List;

public abstract class AbstractBankDeviceController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final BankDeviceService service;

    public AbstractBankDeviceController(BankDeviceService service) {
        this.service = service;
    }

    public BankDeviceDTO create(BankDeviceUpdateDTO dto) {
        log.info("creating BankDeviceDTO from {}", dto);
        return service.create(dto);
    }

    public BankDeviceDTO get(int id) {
        log.info("getting BankDeviceDTO with id {}", id);
        return service.get(id);
    }

    public List<BankDeviceDTO> getAll() {
        log.info("getting all BankDeviceDTO");
        return service.getAll();
    }

    public void update(BankDeviceUpdateDTO dto, int id) {
        log.info("updating BankDeviceDTO {} with {}", id, dto);
        service.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting BankDeviceDTO with id {}", id);
        service.delete(id);
    }
}