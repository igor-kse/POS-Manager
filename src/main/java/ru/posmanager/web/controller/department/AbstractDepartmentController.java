package ru.posmanager.web.controller.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.bank.DepartmentService;
import ru.posmanager.to.bank.DepartmentDTO;

import java.util.List;

public abstract class AbstractDepartmentController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final DepartmentService departmentService;

    public AbstractDepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    public DepartmentDTO create(DepartmentDTO dto) {
        log.info("creating DepartmentDTO from {}", dto);
        return departmentService.create(dto);
    }

    public DepartmentDTO get(int id) {
        log.info("getting DepartmentDTO with id {}", id);
        return departmentService.get(id);
    }

    public List<DepartmentDTO> getAll() {
        log.info("getting all DepartmentDTO");
        return departmentService.getAll();
    }

    public void update(DepartmentDTO dto, int id) {
        log.info("updating DepartmentDTO {} with {}", id, dto);
        departmentService.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting DepartmentDTO with id {}", id);
        departmentService.delete(id);
    }
}