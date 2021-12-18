package ru.posmanager.to.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.BaseDTO;
import ru.posmanager.to.user.UserPreviewDTO;

import java.time.LocalDateTime;
import java.util.Objects;

@Data
@NoArgsConstructor
public class UserCommentDTO extends BaseDTO {

    private Integer requestId;

    private UserPreviewDTO user;

    private String content;

    private LocalDateTime created;

    public UserCommentDTO(UserCommentDTO comment) {
        this(comment.id, comment.requestId, comment.user, comment.content, comment.created);
    }

    public UserCommentDTO(int requestId, UserPreviewDTO user, String content) {
        this(null, requestId, user, content, null);
    }

    public UserCommentDTO(Integer id, Integer requestId, UserPreviewDTO user, String content, LocalDateTime created) {
        super(id);
        this.requestId = requestId;
        this.user = user;
        this.content = content;
        this.created = created;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserCommentDTO that = (UserCommentDTO) o;
        return Objects.equals(requestId, that.requestId) && Objects.equals(user, that.user)
                && Objects.equals(content, that.content) && Objects.equals(created, that.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), requestId, user, content, created);
    }
}
