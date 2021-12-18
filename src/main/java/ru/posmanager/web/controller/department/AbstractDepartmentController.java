package ru.posmanager.web.controller.department;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.bank.DepartmentService;
import ru.posmanager.to.bank.DepartmentDTO;

import java.util.List;

public abstract class AbstractDepartmentController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final DepartmentService service;

    public AbstractDepartmentController(DepartmentService service) {
        this.service = service;
    }

    public DepartmentDTO create(DepartmentDTO to) {
        log.info("creating DepartmentDTO from {}", to);
        return service.create(to);
    }

    public DepartmentDTO get(int id) {
        log.info("getting DepartmentDTO with id {}", id);
        return service.get(id);
    }

    public List<DepartmentDTO> getAll() {
        log.info("getting all DepartmentDTO");
        return service.getAll();
    }

    public void update(DepartmentDTO to, int id) {
        log.info("updating DepartmentDTO {} with {}", id, to);
        service.update(to, id);
    }

    public void delete(int id) {
        log.info("deleting DepartmentDTO with id {}", id);
        service.delete(id);
    }
}