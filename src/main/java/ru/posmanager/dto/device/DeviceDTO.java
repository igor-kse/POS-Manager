package ru.posmanager.dto.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.domain.device.DeviceType;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DeviceDTO extends BaseDTO {

    @NotNull
    private DeviceType deviceType;

    @NotNull
    private VendorDTO vendor;

    @NotNull
    private String model;

    public DeviceDTO(Integer id, VendorDTO vendor, String model, DeviceType deviceType) {
        super(id);
        this.vendor = vendor;
        this.model = model;
        this.deviceType = deviceType;
    }
}