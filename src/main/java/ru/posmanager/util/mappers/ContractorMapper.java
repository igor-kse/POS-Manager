package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.model.bank.Contractor;
import ru.posmanager.to.bank.ContractorDTO;

import java.util.List;

@Component
public class ContractorMapper {

    private final ModelMapper mapper;

    public ContractorMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(Contractor.class, ContractorDTO.class);
    }

    public Contractor toEntity(ContractorDTO dto) {
        return mapper.map(dto, Contractor.class);
    }

    public ContractorDTO toDTO(Contractor entity) {
        return mapper.map(entity, ContractorDTO.class);
    }

    public List<ContractorDTO> toDTO(List<Contractor> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
