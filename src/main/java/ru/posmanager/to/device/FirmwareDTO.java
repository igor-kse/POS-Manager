package ru.posmanager.to.device;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.BaseDTO;

import java.util.Objects;

@Data
@NoArgsConstructor
public class FirmwareDTO extends BaseDTO {

    private VendorDTO vendor;

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
