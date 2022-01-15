package ru.posmanager.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.dto.user.UserPreviewDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class UserCommentDTO extends BaseDTO {

    @NotNull
    @PositiveOrZero
    private Integer requestId;

    @NotNull
    private UserPreviewDTO user;

    @NotNull
    @NotBlank
    @Size(min = 3)
    private String content;

    private LocalDateTime created = LocalDateTime.now();

    public UserCommentDTO(Integer id, Integer requestId, UserPreviewDTO user, String content) {
        super(id);
        this.requestId = requestId;
        this.user = user;
        this.content = content;
    }
}
