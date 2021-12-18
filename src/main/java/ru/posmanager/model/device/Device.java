package ru.posmanager.model.device;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.posmanager.model.BaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "device")
@Access(AccessType.FIELD)
public class Device extends BaseEntity {

    @Column(name = "device_type")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

    @NotNull
    @NotBlank
    @Column(name = "model")
    private String model;

    public Device(Device device) {
        this(device.id, device.vendor, device.model, device.deviceType);
    }

    public Device(Vendor vendor, String model, DeviceType deviceType) {
        this(null, vendor, model, deviceType);
    }

    public Device(Integer id, Vendor vendor, String model, DeviceType deviceType) {
        super(id);
        this.vendor = vendor;
        this.model = model;
        this.deviceType = deviceType;
    }
}
