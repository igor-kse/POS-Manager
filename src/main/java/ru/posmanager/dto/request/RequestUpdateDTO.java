package ru.posmanager.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.domain.request.ImportanceType;
import ru.posmanager.domain.request.RequestType;
import ru.posmanager.domain.request.RequestStatus;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class RequestUpdateDTO extends BaseDTO {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 80)
    private String title;

    @NotNull
    @NotBlank
    @Size(min = 5)
    private String description;

    @NotNull
    @PositiveOrZero
    private Integer bankDeviceId;

    @NotNull
    private RequestType requestType;

    @NotNull
    private ImportanceType importanceType;

    @NotNull
    private RequestStatus requestStatus;

    @NotNull
    @PositiveOrZero
    private Integer authorId;

    private Integer implementorId;

    private LocalDate created;

    private LocalDate modified;

    public RequestUpdateDTO(Integer id, String title, String description, Integer bankDeviceId, RequestType requestType,
                            ImportanceType importanceType, RequestStatus requestStatus, Integer authorId,
                            Integer implementorId, LocalDate created, LocalDate modified) {
        super(id);
        this.title = title;
        this.description = description;
        this.bankDeviceId = bankDeviceId;
        this.requestType = requestType;
        this.importanceType = importanceType;
        this.requestStatus = requestStatus;
        this.authorId = authorId;
        this.implementorId = implementorId;
        this.created = created;
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestUpdateDTO that = (RequestUpdateDTO) o;
        return Objects.equals(title, that.title) && Objects.equals(description, that.description)
                && Objects.equals(bankDeviceId, that.bankDeviceId) && requestType == that.requestType
                && importanceType == that.importanceType && requestStatus == that.requestStatus
                && Objects.equals(authorId, that.authorId) && Objects.equals(implementorId, that.implementorId)
                && Objects.equals(created, that.created) && Objects.equals(modified, that.modified);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, description, bankDeviceId, requestType, importanceType, requestStatus,
                authorId, implementorId, created, modified);
    }

    @Override
    public String toString() {
        return "RequestUpdateDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", bankDeviceId=" + bankDeviceId +
                ", requestType=" + requestType +
                ", importanceType=" + importanceType +
                ", statusType=" + requestStatus +
                ", authorId=" + authorId +
                ", implementorId=" + implementorId +
                ", created=" + created +
                ", modified=" + modified +
                '}';
    }
}
