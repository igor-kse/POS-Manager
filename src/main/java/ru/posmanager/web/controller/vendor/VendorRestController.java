package ru.posmanager.web.controller.vendor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.service.device.VendorService;
import ru.posmanager.to.device.VendorDTO;

import java.util.List;

@RestController
@RequestMapping(value = VendorRestController.VENDOR_PEST_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE)
public class VendorRestController extends AbstractVendorController {
    public static final String VENDOR_PEST_CONTROLLER = "/api/vendors";

    public VendorRestController(VendorService service) {
        super(service);
    }

    @Override
    @GetMapping("/{id}")
    public VendorDTO get(@PathVariable int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<VendorDTO> getAll() {
        return super.getAll();
    }
}