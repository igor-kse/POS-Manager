package ru.posmanager.web.controller.vendor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.posmanager.service.device.VendorService;
import ru.posmanager.dto.device.VendorDTO;

import java.util.List;

@RestController
@RequestMapping(value = VendorRestController.VENDOR_REST_CONTROLLER, produces = MediaType.APPLICATION_JSON_VALUE)
public class VendorRestController extends AbstractVendorController {
    public static final String VENDOR_REST_CONTROLLER = "/api/vendors/";

    public VendorRestController(VendorService vendorService) {
        super(vendorService);
    }

    @Override
    @GetMapping("{id}")
    public VendorDTO get(@PathVariable("id") int vendorId) {
        return super.get(vendorId);
    }

    @Override
    @GetMapping
    public List<VendorDTO> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("filter")
    public List<VendorDTO> getAllFilteredByName(@RequestParam(value = "name", required = false) String name) {
        return super.getAllFilteredByName(name);
    }
}