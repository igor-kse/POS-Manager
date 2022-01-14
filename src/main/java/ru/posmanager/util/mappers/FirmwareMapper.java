package ru.posmanager.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.domain.device.Vendor;
import ru.posmanager.dto.device.FirmwareDTO;
import ru.posmanager.dto.device.FirmwareUpdateDTO;
import ru.posmanager.repository.device.VendorRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class FirmwareMapper {
    private final ModelMapper mapper = new ModelMapper();
    private final VendorRepository vendorRepository;

    public FirmwareMapper(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<FirmwareUpdateDTO, Firmware> firmwareUpdatePostConverter = ctx -> {
            Vendor vendor = vendorRepository.getById(ctx.getSource().getVendorId());
            ctx.getDestination().setVendor(vendor);
            return ctx.getDestination();
        };
        mapper.createTypeMap(Firmware.class, FirmwareDTO.class);
        mapper.createTypeMap(FirmwareUpdateDTO.class, Firmware.class)
                .addMappings(m -> m.skip(Firmware::setVendor))
                .setPostConverter(firmwareUpdatePostConverter);
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

    public List<FirmwareDTO> toDTO(List<Firmware> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
