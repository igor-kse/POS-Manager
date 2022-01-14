package ru.posmanager.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.domain.device.DeviceType;
import ru.posmanager.domain.request.ImportanceType;
import ru.posmanager.domain.request.RequestType;
import ru.posmanager.domain.request.RequestStatus;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.dto.user.UserPreviewDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class RequestPreviewDTO extends BaseDTO {

    @NotNull
    @NotBlank
    @Size(min = 5, max = 80)
    private String title;

    @NotNull
    private DeviceType deviceType;

    @NotNull
    private RequestType requestType;

    @NotNull
    private ImportanceType importanceType;

    @NotNull
    private RequestStatus requestStatus;

    @NotNull
    private UserPreviewDTO author;

    @NotNull
    private UserPreviewDTO implementor;

    private LocalDate created;

    public RequestPreviewDTO(RequestPreviewDTO dto) {
        this(dto.id, dto.title, dto.deviceType, dto.created, dto.requestType, dto.importanceType, dto.requestStatus,
                dto.author, dto.implementor);
    }

    public RequestPreviewDTO(Integer id, String title, DeviceType deviceType, LocalDate created,
                             RequestType requestType, ImportanceType importanceType, RequestStatus requestStatus,
                             UserPreviewDTO author, UserPreviewDTO implementor) {
        super(id);
        this.title = title;
        this.deviceType = deviceType;
        this.created = created;
        this.requestType = requestType;
        this.importanceType = importanceType;
        this.requestStatus = requestStatus;
        this.author = author;
        this.implementor = implementor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        RequestPreviewDTO that = (RequestPreviewDTO) o;
        return Objects.equals(title, that.title) && deviceType == that.deviceType && Objects.equals(created, that.created)
                && requestType == that.requestType && importanceType == that.importanceType
                && requestStatus == that.requestStatus && Objects.equals(author, that.author)
                && Objects.equals(implementor, that.implementor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, deviceType, created, requestType, importanceType, requestStatus,
                author, implementor);
    }
}
