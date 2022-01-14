package ru.posmanager.service.device;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.device.Device;
import ru.posmanager.dto.device.DeviceDTO;
import ru.posmanager.dto.device.DeviceUpdateDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.device.DeviceRepository;
import ru.posmanager.util.mappers.DeviceMapper;

import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Slf4j
@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final DeviceMapper deviceMapper;

    public DeviceService(DeviceRepository deviceRepository, DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    @Transactional
    public DeviceDTO create(DeviceUpdateDTO dto) {
        checkNew(dto);
        Device saved = deviceRepository.save(deviceMapper.toEntity(dto));
        return deviceMapper.toDTO(saved);
    }

    public DeviceDTO get(int id) {
        Device device = deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(Device.class, id));
        return deviceMapper.toDTO(device);
    }

    public List<DeviceDTO> getAll() {
        List<Device> devices = deviceRepository.getAll().orElse(List.of());
        return deviceMapper.toDTO(devices);
    }

    @Transactional
    public void update(DeviceUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(Device.class, id));
        deviceRepository.save(deviceMapper.toEntity(dto));
    }

    public void delete(int id) {
        checkNotFoundWithId(deviceRepository.delete(id) != 0, Device.class, id);
    }
}
