package ru.posmanager.dto.device;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.domain.device.DeviceType;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@NoArgsConstructor
public class DeviceDTO extends BaseDTO {

    @NotNull
    private DeviceType deviceType;

    @NotNull
    private VendorDTO vendor;

    @NotNull
    private String model;

    public DeviceDTO(DeviceDTO to) {
        this(to.id, to.vendor, to.model, to.deviceType);
    }

    public DeviceDTO(VendorDTO vendor, String model, DeviceType deviceType) {
        this(null, vendor, model, deviceType);
    }

    public DeviceDTO(Integer id, VendorDTO vendor, String model, DeviceType deviceType) {
        super(id);
        this.vendor = vendor;
        this.model = model;
        this.deviceType = deviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return deviceType == deviceDTO.deviceType && Objects.equals(vendor, deviceDTO.vendor)
                && Objects.equals(model, deviceDTO.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deviceType, vendor, model);
    }
}