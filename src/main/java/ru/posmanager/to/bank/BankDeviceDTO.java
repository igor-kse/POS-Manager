package ru.posmanager.to.bank;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.posmanager.to.BaseDTO;
import ru.posmanager.to.device.DeviceDTO;
import ru.posmanager.to.device.FirmwareDTO;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Data
@NoArgsConstructor
public class BankDeviceDTO extends BaseDTO {

    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    @Column(name = "tid")
    private String tid;

    @NotNull
    private ContractorDTO contractor;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50)
    private String address;

    @NotNull
    private AffiliateDTO affiliate;

    @NotNull
    private DeviceDTO device;

    @NotNull
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
