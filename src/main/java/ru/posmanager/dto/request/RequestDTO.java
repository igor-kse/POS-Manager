package ru.posmanager.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.domain.request.ImportanceType;
import ru.posmanager.domain.request.RequestStatus;
import ru.posmanager.domain.request.RequestType;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.dto.bank.BankDevicePreviewDTO;
import ru.posmanager.dto.user.UserPreviewDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class RequestDTO extends BaseDTO {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 80)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 5)
    private String description;

    @NotNull
    private BankDevicePreviewDTO bankDevice;

    @NotNull
    private RequestType requestType;

    @NotNull
    private ImportanceType importanceType;

    @NotNull
    private RequestStatus requestStatus;

    @NotNull
    private UserPreviewDTO author;

    private UserPreviewDTO implementor;

    private LocalDate created = LocalDate.now();

    private LocalDate modified;

    private List<UserCommentDTO> comments = new ArrayList<>();

    public RequestDTO(Integer id, String title, String description, BankDevicePreviewDTO bankDevice, RequestType requestType,
                      ImportanceType importanceType, RequestStatus requestStatus, UserPreviewDTO author,
                      UserPreviewDTO implementor, LocalDate created, LocalDate modified, List<UserCommentDTO> comments) {
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

    public List<UserCommentDTO> getComments() {
        return new ArrayList<>(comments);
    }

    public void setComments(List<UserCommentDTO> comments) {
        this.comments = isNull(comments) ? new ArrayList<>() : List.copyOf(comments);
    }
}
