package ru.posmanager.service.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.bank.BankDevice;
import ru.posmanager.dto.bank.BankDeviceDTO;
import ru.posmanager.dto.bank.BankDevicePreviewDTO;
import ru.posmanager.dto.bank.BankDeviceUpdateDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.bank.BankDeviceRepository;
import ru.posmanager.util.mappers.BankDeviceMapper;

import java.util.List;

import static ru.posmanager.util.StringUtil.makeEmptyIfNull;
import static ru.posmanager.util.ValidationUtil.*;

@Slf4j
@Service
public class BankDeviceService {
    private final BankDeviceRepository repository;
    private final BankDeviceMapper bankDeviceMapper;

    public BankDeviceService(BankDeviceRepository bankDeviceRepository, BankDeviceMapper bankDeviceMapper) {
        this.repository = bankDeviceRepository;
        this.bankDeviceMapper = bankDeviceMapper;
    }

    @Transactional
    public BankDeviceDTO create(BankDeviceUpdateDTO dto) {
        checkNew(dto);
        BankDevice saved = repository.save(bankDeviceMapper.toEntity(dto));
        return bankDeviceMapper.toDTO(saved);
    }

    public BankDeviceDTO get(int id) {
        BankDevice bankDevice = repository.findById(id).orElseThrow(() -> new NotFoundException(BankDevice.class, id));
        return bankDeviceMapper.toDTO(bankDevice);
    }

    public List<BankDeviceDTO> getAll() {
        List<BankDevice> bankDevices = repository.getAll().orElse(List.of());
        return bankDeviceMapper.toDTO(bankDevices);
    }

    @Transactional
    public void update(BankDeviceUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        repository.findById(id).orElseThrow(() -> new NotFoundException(BankDevice.class, id));
        repository.save(bankDeviceMapper.toEntity(dto));
    }

    public List<BankDevicePreviewDTO> getAllByTidAndAddress(String tid, String address) {
        List<BankDevice> bankDevices = repository.getAllByTidAndAddress(makeEmptyIfNull(tid), makeEmptyIfNull(address))
                .orElse(List.of());
        return bankDeviceMapper.toPreviewDTO(bankDevices);
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, BankDevice.class, id);
    }
}
