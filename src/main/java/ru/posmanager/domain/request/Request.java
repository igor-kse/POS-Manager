package ru.posmanager.domain.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.posmanager.domain.BaseEntity;
import ru.posmanager.domain.bank.BankDevice;
import ru.posmanager.domain.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.Objects.isNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "request")
@Access(AccessType.FIELD)
public class Request extends BaseEntity {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 80)
    @Column(name = "title")
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 5)
    @Column(name = "description")
    private String description;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "bank_device_id")
    private BankDevice bankDevice;

    @Column(name = "request_type")
    @Enumerated(EnumType.STRING)
    private RequestType requestType;

    @Column(name = "importance_type")
    @Enumerated(EnumType.STRING)
    private ImportanceType importanceType;

    @Column(name = "status_type")
    @Enumerated(EnumType.STRING)
    private RequestStatus requestStatus;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "implementor_id")
    private User implementor;

    @Column(name = "created", nullable = false, columnDefinition = "timestamp default now()")
    private Date created;

    @Column(name = "modified", nullable = false, columnDefinition = "timestamp default now()")
    private Date modified;

    @OneToMany(mappedBy = "request", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<UserComment> comments = new ArrayList<>();

    public Request(Request request) {
        this(request.id, request.title, request.description, request.bankDevice, request.requestType,
                request.importanceType, request.requestStatus, request.author, request.implementor,
                request.created, request.modified, request.comments);
    }

    public Request(String title, String description, BankDevice bankDevice, ImportanceType importanceType,
                   User author) {
        this(null, title, description, bankDevice, RequestType.OTHER, importanceType,
                RequestStatus.NEW, author, null, null, null, null);
    }

    public Request(Integer id, String title, String description, BankDevice bankDevice, RequestType requestType,
                   ImportanceType importanceType, RequestStatus requestStatus, User author, User implementor,
                   Date created, Date modified, List<UserComment> comments) {
        super(id);
        this.title = title;
        this.description = description;
        this.bankDevice = bankDevice;
        this.requestType = requestType;
        this.importanceType = importanceType;
        this.requestStatus = requestStatus;
        this.author = author;
        this.implementor = implementor;
        this.created = created;
        this.modified = modified;
        setComments(comments);
    }

    public List<UserComment> getComments() {
        return new ArrayList<>(comments);
    }

    public void setComments(List<UserComment> comments) {
        this.comments = isNull(comments) ? new ArrayList<>() : List.copyOf(comments);
    }
}
