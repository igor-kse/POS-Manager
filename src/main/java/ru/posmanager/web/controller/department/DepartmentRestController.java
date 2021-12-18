package ru.posmanager.web.controller.department;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.service.bank.DepartmentService;
import ru.posmanager.to.bank.DepartmentDTO;

import java.util.List;

@RestController
@RequestMapping(value = DepartmentRestController.DEPARTMENT_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DepartmentRestController extends AbstractDepartmentController {
    public static final String DEPARTMENT_REST_URL = "/api/departments";

    public DepartmentRestController(DepartmentService service) {
        super(service);
    }

    @Override
    @GetMapping("/{id}")
    public DepartmentDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<DepartmentDTO> getAll() {
        return super.getAll();
    }
}
