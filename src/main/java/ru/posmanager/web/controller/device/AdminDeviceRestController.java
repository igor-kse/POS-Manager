package ru.posmanager.web.controller.device;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.device.DeviceService;
import ru.posmanager.to.device.DeviceDTO;
import ru.posmanager.to.device.DeviceUpdateDTO;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDeviceRestController.DEVICE_ADMIN_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDeviceRestController extends AbstractDeviceController {
    public static final String DEVICE_ADMIN_REST_URL = "/api/admin/devices/";

    public AdminDeviceRestController(DeviceService deviceService) {
        super(deviceService);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceDTO> createWithLocation(@RequestBody @Valid DeviceUpdateDTO deviceUpdateDTO) {
        DeviceDTO created = super.create(deviceUpdateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DEVICE_ADMIN_REST_URL + "{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("{id}")
    public DeviceDTO get(@PathVariable("id") int deviceId) {
        return super.get(deviceId);
    }

    @Override
    @GetMapping
    public List<DeviceDTO> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody @Valid DeviceUpdateDTO deviceUpdateDTO, @PathVariable("id") int deviceId) {
        super.update(deviceUpdateDTO, deviceId);
    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int deviceId) {
        super.delete(deviceId);
    }
}
