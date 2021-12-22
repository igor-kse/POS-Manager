package ru.posmanager.service.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.model.bank.BankDevice;
import ru.posmanager.repository.bank.AffiliateRepository;
import ru.posmanager.repository.bank.BankDeviceRepository;
import ru.posmanager.repository.bank.ContractorRepository;
import ru.posmanager.repository.device.DeviceRepository;
import ru.posmanager.repository.device.FirmwareRepository;
import ru.posmanager.to.bank.BankDeviceDTO;
import ru.posmanager.to.bank.BankDevicePreviewDTO;
import ru.posmanager.to.bank.BankDeviceUpdateDTO;
import ru.posmanager.util.mappers.BankDeviceMapper;

import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.StringUtil.emptyStringIfNull;
import static ru.posmanager.util.ValidationUtil.*;

@Service
public class BankDeviceService {

    private final BankDeviceRepository bankDeviceRepository;
    private final ContractorRepository contractorRepository;
    private final AffiliateRepository affiliateRepository;
    private final DeviceRepository deviceRepository;
    private final FirmwareRepository firmwareRepository;

    private final BankDeviceMapper bankDeviceMapper;
    private final Logger log = LoggerFactory.getLogger(getClass());

    public BankDeviceService(BankDeviceRepository bankDeviceRepository, ContractorRepository contractorRepository,
                             AffiliateRepository affiliateRepository, DeviceRepository deviceRepository,
                             FirmwareRepository firmwareRepository, BankDeviceMapper bankDeviceMapper) {
        this.bankDeviceRepository = bankDeviceRepository;
        this.contractorRepository = contractorRepository;
        this.affiliateRepository = affiliateRepository;
        this.deviceRepository = deviceRepository;
        this.firmwareRepository = firmwareRepository;
        this.bankDeviceMapper = bankDeviceMapper;
    }

    @Transactional
    public BankDeviceDTO create(BankDeviceUpdateDTO dto) {
        checkNew(dto);
        var bankDevice = getEntityFromUpdateDTO(dto);
        bankDevice = bankDeviceRepository.save(bankDevice);
        return bankDeviceMapper.toDTO(bankDevice);
    }

    public BankDeviceDTO get(int id) {
        var bankDevice = bankDeviceRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(BankDevice.class, id));
        return bankDeviceMapper.toDTO(bankDevice);
    }

    public List<BankDeviceDTO> getAll() {
        List<BankDevice> bankDevices = bankDeviceRepository.getAll();
        log.debug("Found the following bankDevices:\n{}", bankDevices);
        return bankDevices != null ? bankDeviceMapper.toDTO(bankDevices) : Collections.emptyList();
    }

    @Transactional
    public void update(BankDeviceUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        bankDeviceRepository.findById(id).orElseThrow(() -> new NotFoundException(BankDevice.class, id));
        var bankDevice = getEntityFromUpdateDTO(dto);
        bankDeviceRepository.save(bankDevice);
    }

    public List<BankDevicePreviewDTO> getAllByTidAndAddress(String tid, String address) {
        List<BankDevice> bankDevices =
                bankDeviceRepository.getAllByTidAndAddress(emptyStringIfNull(tid), emptyStringIfNull(address));
        return bankDeviceMapper.toPreviewDTO(bankDevices);
    }

    public void delete(int id) {
        checkNotFoundWithId(bankDeviceRepository.delete(id) != 0, BankDevice.class, id);
    }

    private BankDevice getEntityFromUpdateDTO(BankDeviceUpdateDTO dto) {
        var bankDevice = bankDeviceMapper.toEntity(dto);
        var contractor = contractorRepository.getById(dto.getContractorId());
        var affiliate = affiliateRepository.getById(dto.getAffiliateId());
        var device = deviceRepository.getById(dto.getDeviceId());
        var firmware = firmwareRepository.getById(dto.getFirmwareId());
        bankDevice.setContractor(contractor);
        bankDevice.setAffiliate(affiliate);
        bankDevice.setDevice(device);
        bankDevice.setFirmware(firmware);
        return bankDevice;
    }
}
