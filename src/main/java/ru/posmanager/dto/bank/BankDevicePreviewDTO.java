package ru.posmanager.dto.bank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.BaseDTO;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class BankDevicePreviewDTO extends BaseDTO {

    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    @Column(name = "tid")
    private String tid;

    @NotNull
    private AffiliateDTO affiliate;

    @NotNull
    @PositiveOrZero
    private Integer deviceId;

    @NotNull
    @PositiveOrZero
    private Integer firmwareId;

    public BankDevicePreviewDTO(Integer id, String tid, AffiliateDTO affiliate, Integer deviceId, Integer firmwareId) {
        super(id);
        this.tid = tid;
        this.affiliate = affiliate;
        this.deviceId = deviceId;
        this.firmwareId = firmwareId;
    }
}
