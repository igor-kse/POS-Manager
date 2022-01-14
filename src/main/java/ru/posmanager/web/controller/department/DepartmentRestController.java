package ru.posmanager.web.controller.department;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.service.bank.DepartmentService;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.web.validator.UniqueValidator;

import java.util.List;

@RestController
@RequestMapping(value = DepartmentRestController.DEPARTMENT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentRestController extends AbstractDepartmentController {
    public static final String DEPARTMENT_REST_URL = "/api/departments/";

    public DepartmentRestController(DepartmentService departmentService, UniqueValidator uniqueValidator) {
        super(departmentService, uniqueValidator);
    }

    @Override
    @GetMapping("{id}")
    public DepartmentDTO get(@PathVariable("id") int departmentId) {
        return super.get(departmentId);
    }

    @Override
    @GetMapping
    public List<DepartmentDTO> getAll() {
        return super.getAll();
    }
}
