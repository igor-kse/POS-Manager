package ru.posmanager.model.bank;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.posmanager.model.BaseEntity;
import ru.posmanager.model.device.Device;
import ru.posmanager.model.device.Firmware;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "bank_device")
@Access(AccessType.FIELD)
public class BankDevice extends BaseEntity {

    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    @Column(name = "tid")
    private String tid;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "contractor_id")
    private Contractor contractor;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50)
    @Column(name = "address")
    private String address;

    @NotNull
    @OneToOne
    @JoinColumn(name = "affiliate_id")
    private Affiliate affiliate;

    @NotNull
    @OneToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @NotNull
    @OneToOne
    @JoinColumn(name = "firmware_id")
    private Firmware firmware;

    public BankDevice(BankDevice bd) {
        this(bd.id, bd.tid, bd.contractor, bd.address, bd.affiliate, bd.device, bd.firmware);
    }

    public BankDevice(String tid, String address, Affiliate affiliate, Device device, Firmware firmware) {
        this(null, tid, null, address, affiliate, device, firmware);
    }

    public BankDevice(String tid, Contractor contractor, String address, Affiliate affiliate,
                      Device device, Firmware firmware) {
        this(null, tid, contractor, address, affiliate, device, firmware);
    }

    public BankDevice(Integer id, String tid, Contractor contractor, String address, Affiliate affiliate,
                      Device device, Firmware firmware) {
        super(id);
        this.tid = tid;
        this.contractor = contractor;
        this.address = address;
        this.affiliate = affiliate;
        this.device = device;
        this.firmware = firmware;
    }
}
