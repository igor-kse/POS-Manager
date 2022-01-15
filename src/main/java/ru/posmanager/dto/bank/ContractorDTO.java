package ru.posmanager.dto.bank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.NamedDTO;
import ru.posmanager.web.validator.UniqueDTO;
import ru.posmanager.web.validator.UniqueDataResolver;
import ru.posmanager.web.validator.UniqueDataType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ContractorDTO extends NamedDTO implements UniqueDTO {

    @NotNull
    @NotBlank
    @Size(min = 9, max = 9)
    private String unp;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 30)
    private String city;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 100)
    private String address;

    public ContractorDTO(Integer id, String name, String unp, String city, String address) {
        super(id, name);
        this.unp = unp;
        this.city = city;
        this.address = address;
    }

    @Override
    public Map<UniqueDataType, Object> resolveType() {
        return UniqueDataResolver.resolve(this);
    }
}
