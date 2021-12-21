package ru.posmanager.web.controller.vendor;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.device.VendorService;
import ru.posmanager.to.device.VendorDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminVendorRestController.VENDOR_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminVendorRestController extends AbstractVendorController {
    public static final String VENDOR_REST_URL = "/api/admin/vendors";

    public AdminVendorRestController(VendorService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<VendorDTO> createWithLocation(@RequestBody VendorDTO vendorDTO) {
        VendorDTO created = super.create(vendorDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VENDOR_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("/{id}")
    public VendorDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<VendorDTO> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/filter")
    public List<VendorDTO> getAllFilteredByName(@RequestParam(value = "name", required = false) String name) {
        return super.getAllFilteredByName(name);
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody VendorDTO to, @PathVariable int id) {
        super.update(to, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
