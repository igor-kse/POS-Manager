package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.device.Device;
import ru.posmanager.dto.device.DeviceDTO;
import ru.posmanager.dto.device.DeviceUpdateDTO;

import java.util.List;

@Component
public class DeviceMapper {

    private final ModelMapper mapper;

    public DeviceMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(Device.class, DeviceDTO.class);
        mapper.createTypeMap(Device.class, DeviceUpdateDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getVendor().getId(), DeviceUpdateDTO::setVendorId));
    }

    public Device toEntity(DeviceDTO dto) {
        return mapper.map(dto, Device.class);
    }

    public Device toEntity(DeviceUpdateDTO dto) {
        return mapper.map(dto, Device.class);
    }

    public DeviceDTO toDTO(Device entity) {
        return mapper.map(entity, DeviceDTO.class);
    }

    public List<DeviceDTO> toDTO(List<Device> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
