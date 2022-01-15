package ru.posmanager.dto.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.domain.request.ImportanceType;
import ru.posmanager.domain.request.RequestStatus;
import ru.posmanager.domain.request.RequestType;
import ru.posmanager.dto.BaseDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}
