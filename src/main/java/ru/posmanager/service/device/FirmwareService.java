package ru.posmanager.service.device;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.dto.device.FirmwareDTO;
import ru.posmanager.dto.device.FirmwareUpdateDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.device.FirmwareRepository;
import ru.posmanager.util.mappers.FirmwareMapper;

import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class FirmwareService {
    private final FirmwareRepository firmwareRepository;
    private final FirmwareMapper firmwareMapper;

    public FirmwareService(FirmwareRepository firmwareRepository, FirmwareMapper firmwareMapper) {
        this.firmwareRepository = firmwareRepository;
        this.firmwareMapper = firmwareMapper;
    }

    @Transactional
    public FirmwareDTO create(FirmwareUpdateDTO dto) {
        checkNew(dto);
        Firmware saved = firmwareRepository.save(firmwareMapper.toEntity(dto));
        return firmwareMapper.toDTO(saved);
    }

    public FirmwareDTO get(int id) {
        Firmware firmware = firmwareRepository.findById(id).orElseThrow(() -> new NotFoundException(Firmware.class, id));
        return firmwareMapper.toDTO(firmware);
    }

    public List<FirmwareDTO> getAll() {
        List<Firmware> firmwares = firmwareRepository.getAll().orElse(List.of());
        return firmwareMapper.toDTO(firmwares);
    }

    @Transactional
    public void update(FirmwareUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        firmwareRepository.findById(id).orElseThrow(() -> new NotFoundException(Firmware.class, id));
        firmwareRepository.save(firmwareMapper.toEntity(dto));
    }

    public void delete(int id) {
        checkNotFoundWithId(firmwareRepository.delete(id) != 0, Firmware.class, id);
    }
}
