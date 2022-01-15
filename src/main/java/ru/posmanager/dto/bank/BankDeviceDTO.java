package ru.posmanager.dto.bank;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.dto.device.DeviceDTO;
import ru.posmanager.dto.device.FirmwareDTO;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
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
}
