package ru.posmanager.dto.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.domain.device.DeviceType;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DeviceUpdateDTO extends BaseDTO {

    @NotNull
    private DeviceType deviceType;

    @NotNull
    @PositiveOrZero
    private Integer vendorId;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 80)
    private String model;

    public DeviceUpdateDTO(Integer id, Integer vendorId, String model, DeviceType deviceType) {
        super(id);
        this.vendorId = vendorId;
        this.model = model;
        this.deviceType = deviceType;
    }
}
