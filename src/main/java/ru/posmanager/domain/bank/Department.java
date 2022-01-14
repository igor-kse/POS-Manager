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
@Table(name = "department")
@Access(AccessType.FIELD)
public class Department extends NamedEntity {

    public Department(Department department) {
        this(department.id, department.name);
    }

    public Department(String name) {
        this(null, name);
    }

    public Department(Integer id, String name) {
        super(id, name);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
