package ru.posmanager.web.controller.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import ru.posmanager.service.bank.DepartmentService;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.web.validator.UniqueValidator;

import java.util.List;

public abstract class AbstractDepartmentController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final DepartmentService departmentService;
    private final UniqueValidator uniqueValidator;

    public AbstractDepartmentController(DepartmentService departmentService, UniqueValidator uniqueValidator) {
        this.departmentService = departmentService;
        this.uniqueValidator = uniqueValidator;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(uniqueValidator);
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