package ru.posmanager.to.bank;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.BaseDTO;
import ru.posmanager.to.device.DeviceDTO;
import ru.posmanager.to.device.FirmwareDTO;

import java.util.Objects;

@Data
@NoArgsConstructor
public class BankDeviceDTO extends BaseDTO {

    private String tid;

    private String address;

    private ContractorDTO contractor;

    private AffiliateDTO affiliate;

    private DeviceDTO device;

    private FirmwareDTO firmware;

    public BankDeviceDTO(Integer id, String tid, String address, ContractorDTO contractor, AffiliateDTO affiliate,
                         DeviceDTO device, FirmwareDTO firmware) {
        super(id);
        this.tid = tid;
        this.address = address;
        this.contractor = contractor;
        this.affiliate = affiliate;
        this.device = device;
        this.firmware = firmware;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BankDeviceDTO that = (BankDeviceDTO) o;
        return Objects.equals(tid, that.tid) && Objects.equals(contractor, that.contractor)
                && Objects.equals(address, that.address) && Objects.equals(affiliate, that.affiliate)
                && Objects.equals(device, that.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tid, contractor, address, affiliate, device);
    }
}
