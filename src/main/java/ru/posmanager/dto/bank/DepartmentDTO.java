package ru.posmanager.dto.bank;

import lombok.*;
import ru.posmanager.dto.NamedDTO;
import ru.posmanager.web.validator.UniqueDTO;
import ru.posmanager.web.validator.UniqueDataResolver;
import ru.posmanager.web.validator.UniqueDataType;

import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class DepartmentDTO extends NamedDTO implements UniqueDTO {

    public DepartmentDTO(Integer id, String name) {
        super(id, name);
    }

    @Override
    public Map<UniqueDataType, Object> resolveType() {
        return UniqueDataResolver.resolve(this);
    }
}
