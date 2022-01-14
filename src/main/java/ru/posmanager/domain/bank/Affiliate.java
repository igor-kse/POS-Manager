package ru.posmanager.domain.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.posmanager.domain.NamedEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
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

    @Override
    public String toString() {
        return "Affiliate{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
