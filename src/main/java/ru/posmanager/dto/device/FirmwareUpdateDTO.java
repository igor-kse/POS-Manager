package ru.posmanager.dto.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class FirmwareUpdateDTO extends BaseDTO {

    @NotNull
    @PositiveOrZero
    private Integer vendorId;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String version;

    public FirmwareUpdateDTO(Integer id, Integer vendorId, String version) {
        super(id);
        this.vendorId = vendorId;
        this.version = version;
    }
}
