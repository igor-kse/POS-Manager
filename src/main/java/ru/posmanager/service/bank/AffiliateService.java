package ru.posmanager.service.bank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.model.bank.Affiliate;
import ru.posmanager.repository.bank.AffiliateRepository;
import ru.posmanager.to.bank.AffiliateDTO;
import ru.posmanager.util.mappers.AffiliateMapper;

import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class AffiliateService {
    private final AffiliateRepository repository;

    private final AffiliateMapper affiliateMapper;

    public AffiliateService(AffiliateRepository repository, AffiliateMapper affiliateMapper) {
        this.repository = repository;
        this.affiliateMapper = affiliateMapper;
    }

    public AffiliateDTO create(AffiliateDTO affiliateDTO) {
        checkNew(affiliateDTO);
        Affiliate affiliate = affiliateMapper.toEntity(affiliateDTO);
        return affiliateMapper.toDTO(repository.save(affiliate));
    }

    public AffiliateDTO get(int id) {
        Affiliate affiliate = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Affiliate.class, id));
        return affiliateMapper.toDTO(affiliate);
    }

    public List<AffiliateDTO> getAll() {
        List<Affiliate> affiliates = repository.getAll();
        return affiliates != null ? affiliateMapper.toDTO(affiliates) : Collections.emptyList();
    }

    @Transactional
    public void update(AffiliateDTO dto, int id) {
        assureIdConsistent(dto, id);
        repository.findById(id).orElseThrow(() -> new NotFoundException(Affiliate.class, id));
        repository.save(affiliateMapper.toEntity(dto));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Affiliate.class, id);
    }
}
