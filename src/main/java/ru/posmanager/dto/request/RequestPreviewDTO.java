package ru.posmanager.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.domain.device.DeviceType;
import ru.posmanager.domain.request.ImportanceType;
import ru.posmanager.domain.request.RequestStatus;
import ru.posmanager.domain.request.RequestType;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.dto.user.UserPreviewDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}
