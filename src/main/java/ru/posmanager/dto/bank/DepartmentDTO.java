package ru.posmanager.dto.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.posmanager.dto.NamedDTO;

@Getter
@Setter
@NoArgsConstructor
public class DepartmentDTO extends NamedDTO {

    public DepartmentDTO(DepartmentDTO to) {
        this(to.id, to.name);
    }

    public DepartmentDTO(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "DepartmentTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
