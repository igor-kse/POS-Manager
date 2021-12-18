package ru.posmanager.web.controller.firmware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.device.FirmwareService;
import ru.posmanager.to.device.FirmwareDTO;
import ru.posmanager.to.device.FirmwareUpdateDTO;

import java.util.List;

public abstract class AbstractFirmwareController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FirmwareService service;

    public AbstractFirmwareController(FirmwareService service) {
        this.service = service;
    }

    public FirmwareDTO create(FirmwareUpdateDTO to) {
        log.info("creating FirmwareDTO from {}", to);
        return service.create(to);
    }

    public FirmwareDTO get(int id) {
        log.info("getting FirmwareDTO with id {}", id);
        return service.get(id);
    }

    public List<FirmwareDTO> getAll() {
        log.info("getting all FirmwareDTO");
        return service.getAll();
    }

    public void update(FirmwareUpdateDTO dto, int id) {
        log.info("updating FirmwareDTO {} with {}", id, dto);
        service.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting FirmwareDTO with id {}", id);
        service.delete(id);
    }
}