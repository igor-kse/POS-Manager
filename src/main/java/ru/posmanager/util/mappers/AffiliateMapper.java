package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.bank.Affiliate;
import ru.posmanager.dto.bank.AffiliateDTO;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class AffiliateMapper {
    private final ModelMapper mapper = new ModelMapper();

    @PostConstruct
    public void setup() {
        mapper.createTypeMap(Affiliate.class, AffiliateDTO.class);
    }

    public Affiliate toEntity(AffiliateDTO dto) {
        return mapper.map(dto, Affiliate.class);
    }

    public AffiliateDTO toDTO(Affiliate entity) {
        return mapper.map(entity, AffiliateDTO.class);
    }

    public List<AffiliateDTO> toDTO(List<Affiliate> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
