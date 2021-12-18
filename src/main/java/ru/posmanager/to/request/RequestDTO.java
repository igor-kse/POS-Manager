package ru.posmanager.to.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.model.request.ImportanceType;
import ru.posmanager.model.request.RequestType;
import ru.posmanager.model.request.RequestStatus;
import ru.posmanager.to.BaseDTO;
import ru.posmanager.to.bank.BankDevicePreviewDTO;
import ru.posmanager.to.user.UserPreviewDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Objects.isNull;

@Data
@NoArgsConstructor
public class RequestDTO extends BaseDTO {

    private String title;

    private String description;

    private BankDevicePreviewDTO bankDevice;

    private RequestType requestType;

    private ImportanceType importanceType;

    private RequestStatus requestStatus;

    private UserPreviewDTO author;

    private UserPreviewDTO implementor;

    private LocalDate created;

    private LocalDate modified;

    private List<UserCommentDTO> comments;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestDTO requestDTO = (RequestDTO) o;
        return Objects.equals(title, requestDTO.title) && Objects.equals(description, requestDTO.description)
                && Objects.equals(bankDevice, requestDTO.bankDevice) && requestType == requestDTO.requestType
                && importanceType == requestDTO.importanceType && requestStatus == requestDTO.requestStatus
                && Objects.equals(author, requestDTO.author) && Objects.equals(implementor, requestDTO.implementor)
                && Objects.equals(created, requestDTO.created) && Objects.equals(modified, requestDTO.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, bankDevice, requestType, importanceType, requestStatus,
                author, implementor, created, modified);
    }
}
