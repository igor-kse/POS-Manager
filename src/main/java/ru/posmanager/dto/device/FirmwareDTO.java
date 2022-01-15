package ru.posmanager.dto.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class FirmwareDTO extends BaseDTO {

    @NotNull
    private VendorDTO vendor;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String version;

    public FirmwareDTO(Integer id, VendorDTO vendor, String version) {
        super(id);
        this.vendor = vendor;
        this.version = version;
    }
}
