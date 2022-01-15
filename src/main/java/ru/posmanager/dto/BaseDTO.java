package ru.posmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.HasId;

@Data
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public abstract class BaseDTO implements HasId {

    protected Integer id;

    public BaseDTO(Integer id) {
        this.id = id;
    }

    @JsonIgnore
    public boolean isNew() {
        return this.id == null;
    }
}