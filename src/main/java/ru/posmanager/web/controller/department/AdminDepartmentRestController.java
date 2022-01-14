package ru.posmanager.web.controller.department;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.bank.DepartmentService;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.web.validator.UniqueValidator;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDepartmentRestController.DEPARTMENT_ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDepartmentRestController extends AbstractDepartmentController {
    public static final String DEPARTMENT_ADMIN_REST_URL = "/api/admin/departments/";

    public AdminDepartmentRestController(DepartmentService departmentService, UniqueValidator uniqueValidator) {
        super(departmentService, uniqueValidator);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentDTO> createWithLocation(@RequestBody @Valid DepartmentDTO departmentDTO) {
        DepartmentDTO created = super.create(departmentDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DEPARTMENT_ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
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

    @Override
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid DepartmentDTO departmentDTO, @PathVariable("id") int departmentId) {
        super.update(departmentDTO, departmentId);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int departmentId) {
        super.delete(departmentId);
    }
}
