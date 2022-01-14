package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.bank.Affiliate;
import ru.posmanager.domain.bank.BankDevice;
import ru.posmanager.domain.bank.Contractor;
import ru.posmanager.domain.device.Device;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.dto.bank.*;
import ru.posmanager.dto.device.DeviceDTO;
import ru.posmanager.dto.device.FirmwareDTO;

import java.util.List;

@Component
public class BankDeviceMapper {

    private final ModelMapper mapper;

    public BankDeviceMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(BankDevice.class, BankDeviceDTO.class);
        mapper.createTypeMap(Contractor.class, ContractorDTO.class);
        mapper.createTypeMap(Affiliate.class, AffiliateDTO.class);
        mapper.createTypeMap(Device.class, DeviceDTO.class);
        mapper.createTypeMap(Firmware.class, FirmwareDTO.class);

        mapper.createTypeMap(BankDevice.class, BankDevicePreviewDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getDevice().getId(), BankDevicePreviewDTO::setDeviceId))
                .addMappings(mapper -> mapper.map(s -> s.getFirmware().getId(), BankDevicePreviewDTO::setFirmwareId));

        mapper.createTypeMap(BankDeviceDTO.class, BankDevicePreviewDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getDevice().getId(), BankDevicePreviewDTO::setDeviceId))
                .addMappings(mapper -> mapper.map(s -> s.getFirmware().getId(), BankDevicePreviewDTO::setFirmwareId));

        mapper.createTypeMap(BankDevice.class, BankDeviceUpdateDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getDevice().getId(), BankDeviceUpdateDTO::setDeviceId))
                .addMappings(mapper -> mapper.map(s -> s.getAffiliate().getId(), BankDeviceUpdateDTO::setAffiliateId))
                .addMappings(mapper -> mapper.map(s -> s.getContractor().getId(), BankDeviceUpdateDTO::setContractorId))
                .addMappings(mapper -> mapper.map(s -> s.getFirmware().getId(), BankDeviceUpdateDTO::setFirmwareId));
    }

    public BankDevice toEntity(BankDeviceDTO dto) {
        return mapper.map(dto, BankDevice.class);
    }

    public BankDevice toEntity(BankDeviceUpdateDTO dto) {
        return mapper.map(dto, BankDevice.class);
    }

    public BankDeviceDTO toDTO(BankDevice entity) {
        return mapper.map(entity, BankDeviceDTO.class);
    }

    public List<BankDeviceDTO> toDTO(List<BankDevice> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    public BankDeviceUpdateDTO toUpdateDTO(BankDevice entity) {
        return mapper.map(entity, BankDeviceUpdateDTO.class);
    }

    public BankDeviceUpdateDTO toUpdateDTO(BankDeviceDTO dto) {
        return mapper.map(dto, BankDeviceUpdateDTO.class);
    }

    public BankDevicePreviewDTO toPreviewDTO(BankDevice entity) {
        return mapper.map(entity, BankDevicePreviewDTO.class);
    }

    public List<BankDevicePreviewDTO> toPreviewDTO(List<BankDevice> entities) {
        return entities.stream().map(this::toPreviewDTO).toList();
    }

    public BankDevicePreviewDTO toPreviewDTO(BankDeviceDTO dto) {
        return mapper.map(dto, BankDevicePreviewDTO.class);
    }
}