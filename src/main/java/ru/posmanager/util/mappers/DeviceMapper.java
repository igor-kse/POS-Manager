package ru.posmanager.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.device.Device;
import ru.posmanager.domain.device.Vendor;
import ru.posmanager.dto.device.DeviceDTO;
import ru.posmanager.dto.device.DeviceUpdateDTO;
import ru.posmanager.repository.device.VendorRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DeviceMapper {
    private final ModelMapper mapper = new ModelMapper();
    private final VendorRepository vendorRepository;

    public DeviceMapper(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<DeviceUpdateDTO, Device> deviceUpdatePostConverter = ctx -> {
            Vendor vendor = vendorRepository.getById(ctx.getSource().getVendorId());
            ctx.getDestination().setVendor(vendor);
            return ctx.getDestination();
        };
        mapper.createTypeMap(Device.class, DeviceDTO.class);
        mapper.createTypeMap(DeviceUpdateDTO.class, Device.class)
                .addMappings(m -> m.skip(Device::setVendor))
                .setPostConverter(deviceUpdatePostConverter);
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
