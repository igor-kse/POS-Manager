package ru.posmanager.domain.bank;

import lombok.*;
import ru.posmanager.domain.NamedEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "affiliate")
@Access(AccessType.FIELD)
public class Affiliate extends NamedEntity {
    
    public Affiliate(Affiliate affiliate) {
        this(affiliate.id, affiliate.name);
    }

    public Affiliate(Integer id, String name) {
        super(id, name);
    }
}
