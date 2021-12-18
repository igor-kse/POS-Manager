package ru.posmanager.to.bank;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.NamedDTO;

import java.util.Objects;

@Data
@NoArgsConstructor
public class ContractorDTO extends NamedDTO {

    private String unp;

    private String city;

    private String address;

    public ContractorDTO(ContractorDTO to) {
        this(to.id, to.name, to.unp, to.city, to.address);
    }

    public ContractorDTO(String name, String unp, String city, String address) {
        this(null, name, unp, city, address);
    }

    public ContractorDTO(Integer id, String name, String unp, String city, String address) {
        super(id, name);
        this.unp = unp;
        this.city = city;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ContractorDTO that = (ContractorDTO) o;
        return Objects.equals(unp, that.unp) && Objects.equals(city, that.city)
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), unp, city, address);
    }
    
}
