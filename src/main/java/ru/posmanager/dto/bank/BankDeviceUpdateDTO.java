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
public class BankDeviceUpdateDTO extends BaseDTO {

    @NotNull
    @NotBlank
    @Size(min = 8, max = 8)
    private String tid;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 50)
    private String address;

    @NotNull
    @PositiveOrZero
    private Integer contractorId;

    @NotNull
    @PositiveOrZero
    private Integer affiliateId;

    @NotNull
    @PositiveOrZero
    private Integer deviceId;

    @NotNull
    @PositiveOrZero
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
}

