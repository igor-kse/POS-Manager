package ru.posmanager.dto.device;

import lombok.*;
import ru.posmanager.dto.NamedDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class VendorDTO extends NamedDTO {

    public VendorDTO(Integer id, String name) {
        super(id, name);
    }
}
