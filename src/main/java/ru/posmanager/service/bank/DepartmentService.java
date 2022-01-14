package ru.posmanager.service.bank;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.bank.Department;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.bank.DepartmentRepository;
import ru.posmanager.util.mappers.DepartmentMapper;

import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Slf4j
@Service
public class DepartmentService {
    private final DepartmentRepository repository;
    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository repository, DepartmentMapper departmentMapper) {
        this.repository = repository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDTO create(DepartmentDTO dto) {
        checkNew(dto);
        Department saved = repository.save(departmentMapper.toEntity(dto));
        return departmentMapper.toDTO(saved);
    }

    public DepartmentDTO get(int id) {
        Department department = repository.findById(id).orElseThrow(() -> new NotFoundException(Department.class, id));
        return departmentMapper.toDTO(department);
    }

    public List<DepartmentDTO> getAll() {
        List<Department> departments = repository.getAll().orElse(List.of());
        return departmentMapper.toDTO(departments);
    }

    @Transactional
    public void update(DepartmentDTO dto, int id) {
        assureIdConsistent(dto, id);
        repository.findById(id).orElseThrow(() -> new NotFoundException(Department.class, id));
        repository.save(departmentMapper.toEntity(dto));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Department.class, id);
    }
}
