package ru.posmanager.web.controller.firmware;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.posmanager.service.device.FirmwareService;
import ru.posmanager.to.device.FirmwareDTO;

import java.util.List;

@RestController
@RequestMapping(value = FirmwareRestController.FIRMWARE_REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class FirmwareRestController extends AbstractFirmwareController {
    public static final String FIRMWARE_REST_URL = "/api/firmwares/";

    public FirmwareRestController(FirmwareService firmwareService) {
        super(firmwareService);
    }

    @Override
    @GetMapping("{id}")
    public FirmwareDTO get(@PathVariable("id") int firmwareId) {
        return super.get(firmwareId);
    }

    @GetMapping
    public List<FirmwareDTO> getAll() {
        return super.getAll();
    }
}
