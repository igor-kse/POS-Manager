package ru.posmanager.service.bank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.domain.bank.BankDevice;
import ru.posmanager.domain.bank.Contractor;
import ru.posmanager.repository.bank.ContractorRepository;
import ru.posmanager.dto.bank.ContractorDTO;
import ru.posmanager.util.mappers.ContractorMapper;

import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class ContractorService {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final ContractorRepository repository;
    private final ContractorMapper contractorMapper;

    public ContractorService(ContractorRepository repository, ContractorMapper contractorMapper) {
        this.repository = repository;
        this.contractorMapper = contractorMapper;
    }

    public ContractorDTO create(ContractorDTO to) {
        checkNew(to);
        var contractor = repository.save(contractorMapper.toEntity(to));
        return contractorMapper.toDTO(contractor);
    }

    public ContractorDTO get(int id) {
        var contractor = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Contractor.class, id));
        return contractorMapper.toDTO(contractor);
    }

    public List<ContractorDTO> getAll() {
        List<Contractor> contractors = repository.getAll();
        return contractors != null ? contractorMapper.toDTO(contractors) : Collections.emptyList();
    }

    @Transactional
    public void update(ContractorDTO to, int id) {
        assureIdConsistent(to, id);
        repository.findById(id).orElseThrow(() -> new NotFoundException(Contractor.class, id));
        repository.save(contractorMapper.toEntity(to));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, BankDevice.class, id);
    }
}
