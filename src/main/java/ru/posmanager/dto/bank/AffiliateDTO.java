package ru.posmanager.dto.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.posmanager.dto.NamedDTO;

@Getter
@Setter
@NoArgsConstructor
public class AffiliateDTO extends NamedDTO {

    public AffiliateDTO(AffiliateDTO to) {
        this(to.id, to.name);
    }

    public AffiliateDTO(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "AffiliateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}