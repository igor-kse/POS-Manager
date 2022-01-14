package ru.posmanager.service.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.bank.Affiliate;
import ru.posmanager.dto.bank.AffiliateDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.bank.AffiliateRepository;
import ru.posmanager.util.mappers.AffiliateMapper;

import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Slf4j
@Service
public class AffiliateService {
    private final AffiliateRepository repository;
    private final AffiliateMapper affiliateMapper;

    public AffiliateService(AffiliateRepository repository, AffiliateMapper affiliateMapper) {
        this.repository = repository;
        this.affiliateMapper = affiliateMapper;
    }

    public AffiliateDTO create(AffiliateDTO dto) {
        checkNew(dto);
        Affiliate saved = affiliateMapper.toEntity(dto);
        return affiliateMapper.toDTO(repository.save(saved));
    }

    public AffiliateDTO get(int id) {
        Affiliate affiliate = repository.findById(id).orElseThrow(() -> new NotFoundException(Affiliate.class, id));
        return affiliateMapper.toDTO(affiliate);
    }

    public List<AffiliateDTO> getAll() {
        List<Affiliate> affiliates = repository.getAll().orElse(List.of());
        return affiliateMapper.toDTO(affiliates);
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