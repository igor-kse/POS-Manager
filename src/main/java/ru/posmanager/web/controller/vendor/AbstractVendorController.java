package ru.posmanager.web.controller.vendor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.device.VendorService;
import ru.posmanager.to.device.VendorDTO;

import java.util.List;

public abstract class AbstractVendorController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final VendorService service;

    public AbstractVendorController(VendorService service) {
        this.service = service;
    }

    public VendorDTO create(VendorDTO to) {
        log.info("creating Vendor from {}", to);
        return service.create(to);
    }

    public VendorDTO get(int id) {
        log.info("getting Vendor with id {}", id);
        return service.get(id);
    }

    public List<VendorDTO> getAll() {
        log.info("getting all Vendors");
        return service.getAll();
    }

    public void update(VendorDTO to, int id) {
        log.info("updating Vendor {} with {}", id, to);
        service.update(to, id);
    }

    public void delete(int id) {
        log.info("deleting Vendor with id {}", id);
        service.delete(id);
    }
}