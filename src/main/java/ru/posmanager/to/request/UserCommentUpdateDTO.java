package ru.posmanager.to.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.BaseDTO;

import java.util.Objects;

@Data
@NoArgsConstructor
public class UserCommentUpdateDTO extends BaseDTO {

    private Integer requestId;

    private String content;

    public UserCommentUpdateDTO(UserCommentUpdateDTO u) {
        this(u.id, u.requestId, u.content);
    }

    public UserCommentUpdateDTO(Integer id, int requestId, String content) {
        super(id);
        this.requestId = requestId;
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserCommentUpdateDTO that = (UserCommentUpdateDTO) o;
        return Objects.equals(requestId, that.requestId) && Objects.equals(content, that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), requestId, content);
    }
}
