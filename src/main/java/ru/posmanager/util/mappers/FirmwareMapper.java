package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.dto.device.FirmwareDTO;
import ru.posmanager.dto.device.FirmwareUpdateDTO;

import java.util.List;

@Component
public class FirmwareMapper {

    private final ModelMapper mapper;

    public FirmwareMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(Firmware.class, FirmwareDTO.class);
        mapper.createTypeMap(Firmware.class, FirmwareUpdateDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getVendor().getId(), FirmwareUpdateDTO::setVendorId));
    }

    public Firmware toEntity(FirmwareDTO dto) {
        return mapper.map(dto, Firmware.class);
    }

    public Firmware toEntity(FirmwareUpdateDTO dto) {
        return mapper.map(dto, Firmware.class);
    }

    public FirmwareDTO toDTO(Firmware entity) {
        return mapper.map(entity, FirmwareDTO.class);
    }

    public FirmwareUpdateDTO toUpdateDTO(Firmware entity) {
        return mapper.map(entity, FirmwareUpdateDTO.class);
    }

    public List<FirmwareDTO> toDTO(List<Firmware> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
