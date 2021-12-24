package ru.posmanager.web.controller.vendor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.device.VendorService;
import ru.posmanager.to.device.VendorDTO;

import java.util.List;

public abstract class AbstractVendorController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final VendorService vendorService;

    public AbstractVendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    public VendorDTO create(VendorDTO dto) {
        log.info("creating Vendor from {}", dto);
        return vendorService.create(dto);
    }

    public VendorDTO get(int id) {
        log.info("getting Vendor with id {}", id);
        return vendorService.get(id);
    }

    public List<VendorDTO> getAll() {
        log.info("getting all Vendors");
        return vendorService.getAll();
    }

    public List<VendorDTO> getAllFilteredByName(String name) {
        log.info("getting all Vendors filtered by name={}", name);
        return vendorService.getAllVendorDTOFilteredByTitle(name);
    }

    public void update(VendorDTO dto, int id) {
        log.info("updating Vendor {} with {}", id, dto);
        vendorService.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting Vendor with id {}", id);
        vendorService.delete(id);
    }
}