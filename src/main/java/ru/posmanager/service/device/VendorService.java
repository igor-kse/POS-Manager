package ru.posmanager.service.device;

import org.springframework.stereotype.Service;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.model.device.Vendor;
import ru.posmanager.repository.device.VendorRepository;
import ru.posmanager.to.device.VendorDTO;
import ru.posmanager.util.mappers.VendorMapper;

import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.StringUtil.emptyStringIfNull;
import static ru.posmanager.util.ValidationUtil.*;

@Service
public class VendorService {
    private final VendorRepository repository;

    private final VendorMapper vendorModelMapper;

    public VendorService(VendorRepository repository, VendorMapper vendorMapper) {
        this.repository = repository;
        this.vendorModelMapper = vendorMapper;
    }

    public VendorDTO create(VendorDTO to) {
        checkNew(to);
        var vendor = repository.save(vendorModelMapper.toEntity(to));
        return vendorModelMapper.toDTO(vendor);
    }

    public VendorDTO get(int id) {
        var vendor = repository.findById(id).orElseThrow(() -> new NotFoundException(Vendor.class, id));
        return vendorModelMapper.toDTO(vendor);
    }

    public List<VendorDTO> getAll() {
        List<Vendor> vendors = repository.getAll();
        return vendors != null ? vendorModelMapper.toDTO(vendors) : Collections.emptyList();
    }

    public List<VendorDTO> getAllVendorDTOFilteredByTitle(String name) {
        List<Vendor> vendors = repository.getAllFilteredByName(emptyStringIfNull(name));
        return vendors != null ? vendorModelMapper.toDTO(vendors) : Collections.emptyList();
    }

    public void update(VendorDTO to, int id) {
        assureIdConsistent(to, id);
        repository.save(vendorModelMapper.toEntity(to));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Vendor.class, id);
    }
}
