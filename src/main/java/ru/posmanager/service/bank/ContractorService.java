package ru.posmanager.service.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.bank.BankDevice;
import ru.posmanager.domain.bank.Contractor;
import ru.posmanager.dto.bank.ContractorDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.bank.ContractorRepository;
import ru.posmanager.util.mappers.ContractorMapper;

import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Slf4j
@Service
public class ContractorService {
    private final ContractorRepository repository;
    private final ContractorMapper contractorMapper;

    public ContractorService(ContractorRepository repository, ContractorMapper contractorMapper) {
        this.repository = repository;
        this.contractorMapper = contractorMapper;
    }

    public ContractorDTO create(ContractorDTO dto) {
        checkNew(dto);
        Contractor saved = repository.save(contractorMapper.toEntity(dto));
        return contractorMapper.toDTO(saved);
    }

    public ContractorDTO get(int id) {
        Contractor contractor = repository.findById(id).orElseThrow(() -> new NotFoundException(Contractor.class, id));
        return contractorMapper.toDTO(contractor);
    }

    public List<ContractorDTO> getAll() {
        List<Contractor> contractors = repository.getAll().orElse(List.of());
        return contractorMapper.toDTO(contractors);
    }

    @Transactional
    public void update(ContractorDTO dto, int id) {
        assureIdConsistent(dto, id);
        repository.findById(id).orElseThrow(() -> new NotFoundException(Contractor.class, id));
        repository.save(contractorMapper.toEntity(dto));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, BankDevice.class, id);
    }
}
