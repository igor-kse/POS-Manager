package ru.posmanager.web.controller.device;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.posmanager.service.device.DeviceService;
import ru.posmanager.to.device.DeviceDTO;
import ru.posmanager.to.device.DeviceUpdateDTO;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = AdminDeviceRestController.DEVICE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminDeviceRestController extends AbstractDeviceController {
    public static final String DEVICE_REST_URL = "/api/admin/devices";

    public AdminDeviceRestController(DeviceService service) {
        super(service);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceDTO> createWithLocation(@RequestBody DeviceUpdateDTO deviceUpdateDTO) {
        DeviceDTO created = super.create(deviceUpdateDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(DEVICE_REST_URL + "/{id}")
                .buildAndExpand(created.id()).toUri();

        return ResponseEntity.created(uri).body(created);
    }

    @Override
    @GetMapping("/{id}")
    public DeviceDTO get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping
    public List<DeviceDTO> getAll() {
        return super.getAll();
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody DeviceUpdateDTO dto, @PathVariable int id) {
        super.update(dto, id);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }
}
