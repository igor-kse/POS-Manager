package ru.posmanager.web.controller.device;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.device.DeviceService;
import ru.posmanager.dto.device.DeviceDTO;
import ru.posmanager.dto.device.DeviceUpdateDTO;

import java.util.List;

public abstract class AbstractDeviceController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final DeviceService deviceService;

    public AbstractDeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    public DeviceDTO create(DeviceUpdateDTO dto) {
        log.info("creating DeviceDTO from {}", dto);
        return deviceService.create(dto);
    }

    public DeviceDTO get(int id) {
        log.info("getting DeviceDTO with id {}", id);
        return deviceService.get(id);
    }

    public List<DeviceDTO> getAll() {
        log.info("getting all DeviceDTO");
        return deviceService.getAll();
    }

    public void update(DeviceUpdateDTO dto, int id) {
        log.info("updating DeviceDTO {} with {}", id, dto);
        deviceService.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting DeviceDTO with id {}", id);
        deviceService.delete(id);
    }
}