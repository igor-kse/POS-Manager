package ru.posmanager.web.controller.firmware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.device.FirmwareService;
import ru.posmanager.dto.device.FirmwareDTO;
import ru.posmanager.dto.device.FirmwareUpdateDTO;

import java.util.List;

public abstract class AbstractFirmwareController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final FirmwareService firmwareService;

    public AbstractFirmwareController(FirmwareService firmwareService) {
        this.firmwareService = firmwareService;
    }

    public FirmwareDTO create(FirmwareUpdateDTO dto) {
        log.info("creating FirmwareDTO from {}", dto);
        return firmwareService.create(dto);
    }

    public FirmwareDTO get(int id) {
        log.info("getting FirmwareDTO with id {}", id);
        return firmwareService.get(id);
    }

    public List<FirmwareDTO> getAll() {
        log.info("getting all FirmwareDTO");
        return firmwareService.getAll();
    }

    public void update(FirmwareUpdateDTO dto, int id) {
        log.info("updating FirmwareDTO {} with {}", id, dto);
        firmwareService.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting FirmwareDTO with id {}", id);
        firmwareService.delete(id);
    }
}