package ru.posmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import ru.posmanager.HasIdAndName;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class NamedDTO extends BaseDTO implements HasIdAndName {

    @NotBlank
    @Length(min = 2, max = 80)
    protected String name;

    public NamedDTO(Integer id, String name) {
        super(id);
        this.name = name;
    }
}
