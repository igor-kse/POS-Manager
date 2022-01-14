package ru.posmanager.service.device;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.repository.device.FirmwareRepository;
import ru.posmanager.repository.device.VendorRepository;
import ru.posmanager.dto.device.FirmwareDTO;
import ru.posmanager.dto.device.FirmwareUpdateDTO;
import ru.posmanager.util.mappers.FirmwareMapper;

import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class FirmwareService {
    private final FirmwareRepository firmwareRepository;
    private final VendorRepository vendorRepository;
    private final FirmwareMapper firmwareMapper;

    public FirmwareService(FirmwareRepository firmwareRepository, VendorRepository vendorRepository,
                           FirmwareMapper firmwareMapper) {
        this.firmwareRepository = firmwareRepository;
        this.vendorRepository = vendorRepository;
        this.firmwareMapper = firmwareMapper;
    }

    @Transactional
    public FirmwareDTO create(FirmwareUpdateDTO dto) {
        checkNew(dto);
        var vendor = vendorRepository.getById(dto.getVendorId());
        var firmware = firmwareMapper.toEntity(dto);
        firmware.setVendor(vendor);
        firmware = firmwareRepository.save(firmware);
        return firmwareMapper.toDTO(firmware);
    }

    public FirmwareDTO get(int id) {
        var firmware = firmwareRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Firmware.class, id));
        return firmwareMapper.toDTO(firmware);
    }

    public List<FirmwareDTO> getAll() {
        List<Firmware> firmwares = firmwareRepository.getAll();
        return firmwares != null ? firmwareMapper.toDTO(firmwares) : Collections.emptyList();
    }

    @Transactional
    public void update(FirmwareUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        firmwareRepository.findById(id).orElseThrow(() -> new NotFoundException(Firmware.class, id));
        var firmware = firmwareMapper.toEntity(dto);
        var vendor = vendorRepository.getById(dto.getVendorId());
        firmware.setVendor(vendor);
        firmwareRepository.save(firmware);
    }

    public void delete(int id) {
        checkNotFoundWithId(firmwareRepository.delete(id) != 0, Firmware.class, id);
    }
}
