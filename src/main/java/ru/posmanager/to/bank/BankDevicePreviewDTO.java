package ru.posmanager.to.bank;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.BaseDTO;

import java.util.Objects;

@Data
@NoArgsConstructor
public class BankDevicePreviewDTO extends BaseDTO {

    private String tid;

    private AffiliateDTO affiliate;

    private int deviceId;

    private int firmwareId;

    public BankDevicePreviewDTO(BankDevicePreviewDTO dto) {
        this(dto.id, dto.tid, dto.affiliate, dto.deviceId, dto.firmwareId);
    }

    public BankDevicePreviewDTO(String tid, AffiliateDTO affiliate, int deviceId, int firmwareId) {
        this(null, tid, affiliate, deviceId, firmwareId);
    }

    public BankDevicePreviewDTO(Integer id, String tid, AffiliateDTO affiliate, int deviceId, int firmwareId) {
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
        return deviceId == that.deviceId && firmwareId == that.firmwareId && Objects.equals(tid, that.tid) && Objects.equals(affiliate, that.affiliate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tid, affiliate, deviceId, firmwareId);
    }
}
