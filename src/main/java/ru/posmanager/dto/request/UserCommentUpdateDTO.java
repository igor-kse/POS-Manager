package ru.posmanager.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class UserCommentUpdateDTO extends BaseDTO {

    @NotNull
    @PositiveOrZero
    private Integer requestId;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String content;

    public UserCommentUpdateDTO(Integer id, int requestId, String content) {
        super(id);
        this.requestId = requestId;
        this.content = content;
    }
}
