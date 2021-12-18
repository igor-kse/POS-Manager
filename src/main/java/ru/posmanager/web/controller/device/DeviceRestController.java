package ru.posmanager.web.controller.device;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.service.device.DeviceService;
import ru.posmanager.to.device.DeviceDTO;

import java.util.List;

@RestController
@RequestMapping(value = DeviceRestController.DEVICE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DeviceRestController extends AbstractDeviceController {
    public static final String DEVICE_REST_URL = "/api/devices";

    public DeviceRestController(DeviceService service) {
        super(service);
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
}
