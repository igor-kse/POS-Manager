package ru.posmanager.dto.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.posmanager.dto.NamedDTO;

@Getter
@Setter
@NoArgsConstructor
public class VendorDTO extends NamedDTO {

    public VendorDTO(VendorDTO vendorDTO) {
        this(vendorDTO.id, vendorDTO.name);
    }

    public VendorDTO(String name) {
        this(null, name);
    }

    public VendorDTO(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "VendorTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
