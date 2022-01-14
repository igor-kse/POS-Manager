package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.bank.Department;
import ru.posmanager.dto.bank.DepartmentDTO;

import java.util.List;

@Component
public class DepartmentMapper {

    private final ModelMapper mapper;

    public DepartmentMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(Department.class, DepartmentDTO.class);
    }

    public Department toEntity(DepartmentDTO dto) {
        return mapper.map(dto, Department.class);
    }

    public DepartmentDTO toDTO(Department entity) {
        return mapper.map(entity, DepartmentDTO.class);
    }

    public List<DepartmentDTO> toDTO(List<Department> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
