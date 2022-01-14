package ru.posmanager.dto.device;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
public class FirmwareUpdateDTO extends BaseDTO {

    @NotNull
    @PositiveOrZero
    private Integer vendorId;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String version;

    public FirmwareUpdateDTO(FirmwareUpdateDTO firmwareUpdateDTO) {
        this(firmwareUpdateDTO.id, firmwareUpdateDTO.vendorId, firmwareUpdateDTO.version);
    }

    public FirmwareUpdateDTO(Integer vendorId, String version) {
        this(null, vendorId, version);
    }

    public FirmwareUpdateDTO(Integer id, Integer vendorId, String version) {
        super(id);
        this.vendorId = vendorId;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FirmwareUpdateDTO that = (FirmwareUpdateDTO) o;
        return Objects.equals(vendorId, that.vendorId) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vendorId, version);
    }
}
