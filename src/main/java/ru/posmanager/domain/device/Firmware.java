package ru.posmanager.domain.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.posmanager.domain.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "firmware")
@Access(AccessType.FIELD)
public class Firmware extends BaseEntity {

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @NotNull
    @NotBlank
    @Column(name = "version")
    private String version;

    public Firmware(Firmware firmware) {
        this(firmware.id, firmware.vendor, firmware.version);
    }

    public Firmware(Vendor vendor, String version) {
        this(null, vendor, version);
    }

    public Firmware(Integer id, Vendor vendor, String version) {
        super(id);
        this.vendor = vendor;
        this.version = version;
    }
}
