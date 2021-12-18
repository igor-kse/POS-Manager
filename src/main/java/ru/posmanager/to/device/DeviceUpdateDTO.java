package ru.posmanager.to.device;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.model.device.DeviceType;
import ru.posmanager.to.BaseDTO;

import java.util.Objects;

@Data
@NoArgsConstructor
public class DeviceUpdateDTO extends BaseDTO {

    private DeviceType deviceType;

    private Integer vendorId;

    private String model;

    public DeviceUpdateDTO(DeviceUpdateDTO dto) {
        this(dto.id, dto.vendorId, dto.model, dto.deviceType);
    }

    public DeviceUpdateDTO(Integer vendorId, String model, DeviceType deviceType) {
        this(null, vendorId, model, deviceType);
    }

    public DeviceUpdateDTO(Integer id, Integer vendorId, String model, DeviceType deviceType) {
        super(id);
        this.vendorId = vendorId;
        this.model = model;
        this.deviceType = deviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DeviceUpdateDTO that = (DeviceUpdateDTO) o;
        return deviceType == that.deviceType && Objects.equals(vendorId, that.vendorId) && Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), deviceType, vendorId, model);
    }
}
