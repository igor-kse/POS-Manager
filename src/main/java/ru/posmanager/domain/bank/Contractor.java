package ru.posmanager.domain.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.posmanager.domain.NamedEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "contractor")
@Access(AccessType.FIELD)
public class Contractor extends NamedEntity {

    @NotNull
    @NotBlank
    @Size(min = 9, max = 9)
    @Column(name = "unp")
    private String unp;

    @NotNull
    @NotBlank
    @Size(min = 3, max = 30)
    @Column(name = "city")
    private String city;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 100)
    @Column(name = "address")
    private String address;

    public Contractor(Contractor contractor) {
        this(contractor.id, contractor.name, contractor.unp, contractor.city, contractor.address);
    }

    public Contractor(String name, String unp, String city, String address) {
        this(null, name, unp, city, address);
    }

    public Contractor(Integer id, String name, String unp, String city, String address) {
        super(id, name);
        this.unp = unp;
        this.city = city;
        this.address = address;
    }
}
