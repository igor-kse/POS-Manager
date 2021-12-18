package ru.posmanager.to.bank;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.BaseDTO;

import java.util.Objects;

@Data
@NoArgsConstructor
public class BankDeviceUpdateDTO extends BaseDTO {

    private String tid;

    private String address;

    private Integer contractorId;

    private Integer affiliateId;

    private Integer deviceId;

    private Integer firmwareId;

    public BankDeviceUpdateDTO(Integer id, String tid, String address, Integer contractorId, Integer affiliateId,
                               Integer deviceId, Integer firmwareId) {
        super(id);
        this.tid = tid;
        this.address = address;
        this.contractorId = contractorId;
        this.affiliateId = affiliateId;
        this.deviceId = deviceId;
        this.firmwareId = firmwareId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BankDeviceUpdateDTO that = (BankDeviceUpdateDTO) o;
        return Objects.equals(tid, that.tid) && Objects.equals(address, that.address) && Objects.equals(contractorId, that.contractorId) && Objects.equals(affiliateId, that.affiliateId) && Objects.equals(deviceId, that.deviceId) && Objects.equals(firmwareId, that.firmwareId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tid, address, contractorId, affiliateId, deviceId, firmwareId);
    }
}
