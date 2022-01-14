package ru.posmanager.service.device;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.posmanager.domain.device.Vendor;
import ru.posmanager.dto.device.VendorDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.device.VendorRepository;
import ru.posmanager.util.mappers.VendorMapper;

import java.util.List;

import static ru.posmanager.util.StringUtil.makeEmptyIfNull;
import static ru.posmanager.util.ValidationUtil.*;

@Slf4j
@Service
public class VendorService {
    private final VendorRepository repository;
    private final VendorMapper vendorMapper;

    public VendorService(VendorRepository repository, VendorMapper vendorMapper) {
        this.repository = repository;
        this.vendorMapper = vendorMapper;
    }

    public VendorDTO create(VendorDTO dto) {
        checkNew(dto);
        Vendor saved = repository.save(vendorMapper.toEntity(dto));
        return vendorMapper.toDTO(saved);
    }

    public VendorDTO get(int id) {
        Vendor vendor = repository.findById(id).orElseThrow(() -> new NotFoundException(Vendor.class, id));
        return vendorMapper.toDTO(vendor);
    }

    public List<VendorDTO> getAll() {
        List<Vendor> vendors = repository.getAll().orElse(List.of());
        return vendorMapper.toDTO(vendors);
    }

    public List<VendorDTO> getFilteredByTitle(String name) {
        List<Vendor> vendors = repository.getAllFilteredByName(makeEmptyIfNull(name)).orElse(List.of());
        return vendorMapper.toDTO(vendors);
    }

    public void update(VendorDTO dto, int id) {
        assureIdConsistent(dto, id);
        repository.findById(id).orElseThrow(() -> new NotFoundException(Vendor.class, id));
        repository.save(vendorMapper.toEntity(dto));
    }

    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id) != 0, Vendor.class, id);
    }
}
