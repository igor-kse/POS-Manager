package ru.posmanager.dto.bank;

import lombok.*;
import ru.posmanager.dto.NamedDTO;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class AffiliateDTO extends NamedDTO {

    public AffiliateDTO(Integer id, String name) {
        super(id, name);
    }
}