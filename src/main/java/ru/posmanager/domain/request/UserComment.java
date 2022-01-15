package ru.posmanager.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.posmanager.domain.BaseEntity;
import ru.posmanager.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table(name = "user_comment")
@Access(AccessType.FIELD)
public class UserComment extends BaseEntity {

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private Request request;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @NotBlank
    @Size(min = 3)
    @Column(name = "content")
    private String content;

    @Column(name = "created", columnDefinition = "timestamp default now()")
    private LocalDateTime created;

    public UserComment(UserComment comment) {
        this(comment.id, comment.request, comment.user, comment.content, comment.created);
    }

    public UserComment(Request request, User user, String content) {
        this(null, request, user, content, null);
    }

    public UserComment(Integer id, Request request, User user, String content, LocalDateTime created) {
        super(id);
        this.request = request;
        this.user = user;
        this.content = content;
        this.created = created;
    }
}
