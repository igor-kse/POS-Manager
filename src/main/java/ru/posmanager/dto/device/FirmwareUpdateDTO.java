package ru.posmanager.dto.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.web.validator.UniqueDTO;
import ru.posmanager.web.validator.UniqueDataResolver;
import ru.posmanager.web.validator.UniqueDataType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class FirmwareUpdateDTO extends BaseDTO implements UniqueDTO {

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

    @Override
    public Map<UniqueDataType, Object> resolveType() {
        return UniqueDataResolver.resolve(this);
    }
}
