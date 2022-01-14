package ru.posmanager.dto.bank;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.dto.BaseDTO;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
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

    public BankDevicePreviewDTO(BankDevicePreviewDTO dto) {
        this(dto.id, dto.tid, dto.affiliate, dto.deviceId, dto.firmwareId);
    }

    public BankDevicePreviewDTO(String tid, AffiliateDTO affiliate, Integer deviceId, Integer firmwareId) {
        this(null, tid, affiliate, deviceId, firmwareId);
    }

    public BankDevicePreviewDTO(Integer id, String tid, AffiliateDTO affiliate, Integer deviceId, Integer firmwareId) {
        super(id);
        this.tid = tid;
        this.affiliate = affiliate;
        this.deviceId = deviceId;
        this.firmwareId = firmwareId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BankDevicePreviewDTO that = (BankDevicePreviewDTO) o;
        return Objects.equals(tid, that.tid) && Objects.equals(affiliate, that.affiliate)
                && Objects.equals(deviceId, that.deviceId) && Objects.equals(firmwareId, that.firmwareId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tid, affiliate, deviceId, firmwareId);
    }
}
