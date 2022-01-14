package ru.posmanager.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.bank.Affiliate;
import ru.posmanager.domain.bank.BankDevice;
import ru.posmanager.domain.bank.Contractor;
import ru.posmanager.domain.device.Device;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.dto.bank.BankDeviceDTO;
import ru.posmanager.dto.bank.BankDevicePreviewDTO;
import ru.posmanager.dto.bank.BankDeviceUpdateDTO;
import ru.posmanager.repository.bank.AffiliateRepository;
import ru.posmanager.repository.bank.ContractorRepository;
import ru.posmanager.repository.device.DeviceRepository;
import ru.posmanager.repository.device.FirmwareRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class BankDeviceMapper {
    private final ModelMapper mapper = new ModelMapper();
    private final DeviceRepository deviceRepository;
    private final FirmwareRepository firmwareRepository;
    private final AffiliateRepository affiliateRepository;
    private final ContractorRepository contractorRepository;

    public BankDeviceMapper(DeviceRepository deviceRepository, FirmwareRepository firmwareRepository,
                            AffiliateRepository affiliateRepository, ContractorRepository contractorRepository) {
        this.deviceRepository = deviceRepository;
        this.firmwareRepository = firmwareRepository;
        this.affiliateRepository = affiliateRepository;
        this.contractorRepository = contractorRepository;
    }

    @PostConstruct
    public void setup() {
        mapper.createTypeMap(BankDevice.class, BankDeviceDTO.class);
        mapper.createTypeMap(BankDevice.class, BankDevicePreviewDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getDevice().getId(), BankDevicePreviewDTO::setDeviceId))
                .addMappings(mapper -> mapper.map(s -> s.getFirmware().getId(), BankDevicePreviewDTO::setFirmwareId));

        Converter<BankDeviceUpdateDTO, BankDevice> bankDeviceUpdatePostConverter = ctx -> {
            BankDeviceUpdateDTO source = ctx.getSource();
            BankDevice bankDevice = ctx.getDestination();
            Device device = deviceRepository.getById(source.getDeviceId());
            Firmware firmware = firmwareRepository.getById(source.getFirmwareId());
            Affiliate affiliate = affiliateRepository.getById(source.getAffiliateId());
            Contractor contractor = contractorRepository.getById(source.getContractorId());
            bankDevice.setDevice(device);
            bankDevice.setFirmware(firmware);
            bankDevice.setAffiliate(affiliate);
            bankDevice.setContractor(contractor);
            return bankDevice;
        };
        mapper.createTypeMap(BankDeviceUpdateDTO.class, BankDevice.class)
                .addMappings(m -> m.skip(BankDevice::setDevice))
                .addMappings(m -> m.skip(BankDevice::setAffiliate))
                .addMappings(m -> m.skip(BankDevice::setContractor))
                .addMappings(m -> m.skip(BankDevice::setFirmware))
                .setPostConverter(bankDeviceUpdatePostConverter);
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

    public BankDevicePreviewDTO toPreviewDTO(BankDevice entity) {
        return mapper.map(entity, BankDevicePreviewDTO.class);
    }

    public List<BankDevicePreviewDTO> toPreviewDTO(List<BankDevice> entities) {
        return entities.stream().map(this::toPreviewDTO).toList();
    }
}