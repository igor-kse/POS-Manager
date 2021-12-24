package ru.posmanager.web.controller.firmware;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.device.FirmwareService;
import ru.posmanager.to.device.FirmwareDTO;
import ru.posmanager.to.device.FirmwareUpdateDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminFirmwareRestController.FIRMWARE_ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminFirmwareRestController extends AbstractFirmwareController {
    public static final String FIRMWARE_ADMIN_REST_URL = "/api/admin/firmwares/";

    public AdminFirmwareRestController(FirmwareService firmwareService) {
        super(firmwareService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FirmwareDTO> createWithLocation(@RequestBody FirmwareUpdateDTO firmwareUpdateDTO) {
        FirmwareDTO created = super.create(firmwareUpdateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(FIRMWARE_ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("{id}")
    public FirmwareDTO get(@PathVariable("id") int firmwareId) {
        return super.get(firmwareId);
    }

    @Override
    @GetMapping
    public List<FirmwareDTO> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody FirmwareUpdateDTO firmwareUpdateDTO, @PathVariable("id") int firmwareId) {
        super.update(firmwareUpdateDTO, firmwareId);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int firmwareId) {
        super.delete(firmwareId);
    }
}
