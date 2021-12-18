package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.model.device.Vendor;
import ru.posmanager.to.device.VendorDTO;

import java.util.List;

@Component
public class VendorMapper {

    private final ModelMapper mapper;

    public VendorMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(Vendor.class, VendorDTO.class);
    }

    public Vendor toEntity(VendorDTO dto) {
        return mapper.map(dto, Vendor.class);
    }

    public VendorDTO toDTO(Vendor entity) {
        return mapper.map(entity, VendorDTO.class);
    }

    public List<VendorDTO> toDTO(List<Vendor> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
