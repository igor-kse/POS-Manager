package ru.posmanager.to.request;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.model.device.DeviceType;
import ru.posmanager.model.request.ImportanceType;
import ru.posmanager.model.request.RequestType;
import ru.posmanager.model.request.RequestStatus;
import ru.posmanager.to.BaseDTO;
import ru.posmanager.to.user.UserPreviewDTO;

import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
public class RequestPreviewDTO extends BaseDTO {

    private String title;

    private DeviceType deviceType;

    private RequestType requestType;

    private ImportanceType importanceType;

    private RequestStatus requestStatus;

    private UserPreviewDTO author;

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
