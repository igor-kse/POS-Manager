package ru.posmanager.service.bank;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.domain.bank.Department;
import ru.posmanager.repository.bank.DepartmentRepository;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.util.mappers.DepartmentMapper;

import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class DepartmentService {
    private final DepartmentRepository repository;

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository repository, DepartmentMapper departmentMapper) {
        this.repository = repository;
        this.departmentMapper = departmentMapper;
    }

    public DepartmentDTO create(DepartmentDTO to) {
        checkNew(to);
        var department = repository.save(departmentMapper.toEntity(to));
        return departmentMapper.toDTO(department);
    }

    public DepartmentDTO get(int id) {
        var department = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(Department.class, id));
        return departmentMapper.toDTO(department);
    }

    public List<DepartmentDTO> getAll() {
        List<Department> departments = repository.getAll();
        return departments != null ? departmentMapper.toDTO(departments) : Collections.emptyList();
    }

    @Transactional
    public void update(DepartmentDTO to, int id) {
        assureIdConsistent(to, id);
        repository.findById(id).orElseThrow(() -> new NotFoundException(Department.class, id));
        repository.save(departmentMapper.toEntity(to));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Department.class, id);
    }
}
