package ru.posmanager.service.device;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.domain.device.Device;
import ru.posmanager.repository.device.DeviceRepository;
import ru.posmanager.repository.device.VendorRepository;
import ru.posmanager.dto.device.DeviceDTO;
import ru.posmanager.dto.device.DeviceUpdateDTO;
import ru.posmanager.util.mappers.DeviceMapper;

import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    private final VendorRepository vendorRepository;
    private final DeviceMapper deviceMapper;

    public DeviceService(DeviceRepository deviceRepository, VendorRepository vendorRepository, DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.vendorRepository = vendorRepository;
        this.deviceMapper = deviceMapper;
    }

    @Transactional
    public DeviceDTO create(DeviceUpdateDTO dto) {
        checkNew(dto);
        var device = deviceMapper.toEntity(dto);
        var vendor = vendorRepository.getById(dto.getVendorId());
        device.setVendor(vendor);
        device = deviceRepository.save(device);
        return deviceMapper.toDTO(device);
    }

    public DeviceDTO get(int id) {
        var device = deviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Device.class, id));
        return deviceMapper.toDTO(device);
    }

    public List<DeviceDTO> getAll() {
        List<Device> devices = deviceRepository.getAll();
        return devices != null ? deviceMapper.toDTO(devices) : Collections.emptyList();
    }

    @Transactional
    public void update(DeviceUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(Device.class, id));
        var device = deviceMapper.toEntity(dto);
        device.setVendor(vendorRepository.getById(dto.getVendorId()));
        deviceRepository.save(device);
    }

    public void delete(int id) {
        checkNotFoundWithId(deviceRepository.delete(id) != 0, Device.class, id);
    }
}
