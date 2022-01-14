package ru.posmanager.dto.device;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
public class FirmwareDTO extends BaseDTO {

    @NotNull
    private VendorDTO vendor;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String version;

    public FirmwareDTO(FirmwareDTO firmwareDTO) {
        this(firmwareDTO.id, firmwareDTO.vendor, firmwareDTO.version);
    }

    public FirmwareDTO(VendorDTO vendor, String version) {
        this(null, vendor, version);
    }

    public FirmwareDTO(Integer id, VendorDTO vendor, String version) {
        super(id);
        this.vendor = vendor;
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FirmwareDTO that = (FirmwareDTO) o;
        return Objects.equals(vendor, that.vendor) && Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), vendor, version);
    }
}
