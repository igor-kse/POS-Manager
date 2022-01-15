package ru.posmanager.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.posmanager.HasId;
import ru.posmanager.domain.bank.Affiliate;
import ru.posmanager.domain.bank.Contractor;
import ru.posmanager.domain.bank.Department;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.domain.user.User;
import ru.posmanager.dto.bank.AffiliateDTO;
import ru.posmanager.dto.bank.ContractorDTO;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.dto.device.FirmwareUpdateDTO;
import ru.posmanager.dto.user.UserUpdateDTO;
import ru.posmanager.repository.bank.AffiliateRepository;
import ru.posmanager.repository.bank.ContractorRepository;
import ru.posmanager.repository.bank.DepartmentRepository;
import ru.posmanager.repository.device.FirmwareRepository;
import ru.posmanager.repository.user.UserRepository;

import java.util.EnumMap;
import java.util.Map;

import static ru.posmanager.web.ExceptionInfoHandler.*;

@Component
public class UniqueDataResolver {
    private static DepartmentRepository departmentRepository;
    private static UserRepository userRepository;
    private static ContractorRepository contractorRepository;
    private static FirmwareRepository firmwareRepository;
    private static AffiliateRepository affiliateRepository;

    @Autowired
    public UniqueDataResolver(DepartmentRepository departmentRepository, UserRepository userRepository,
                              ContractorRepository contractorRepository, FirmwareRepository firmwareRepository,
                              AffiliateRepository affiliateRepository) {
        UniqueDataResolver.departmentRepository = departmentRepository;
        UniqueDataResolver.userRepository = userRepository;
        UniqueDataResolver.contractorRepository = contractorRepository;
        UniqueDataResolver.firmwareRepository = firmwareRepository;
        UniqueDataResolver.affiliateRepository = affiliateRepository;
    }

    public static Map<UniqueDataType, Object> resolve(AffiliateDTO affiliateDTO) {
        Affiliate affiliate = affiliateRepository.getByName(affiliateDTO.getName()).orElse(null);
        return getResolvedData("name", EXCEPTION_DUPLICATE_AFFILIATE, affiliate);
    }

    public static Map<UniqueDataType, Object> resolve(DepartmentDTO departmentDTO) {
        Department department = departmentRepository.getByName(departmentDTO.getName()).orElse(null);
        return getResolvedData("name", EXCEPTION_DUPLICATE_DEPARTMENT, department);
    }

    public static Map<UniqueDataType, Object> resolve(ContractorDTO contractorDTO) {
        Contractor contractor = contractorRepository.getByUnp(contractorDTO.getUnp()).orElse(null);
        return getResolvedData("unp", EXCEPTION_DUPLICATE_UNP, contractor);
    }

    public static Map<UniqueDataType, Object> resolve(FirmwareUpdateDTO firmwareUpdateDTO) {
        int vendorId = firmwareUpdateDTO.getVendorId();
        String version = firmwareUpdateDTO.getVersion();
        Firmware firmware = firmwareRepository.getByVendorAndVersion(vendorId, version).orElse(null);
        return getResolvedData("version", EXCEPTION_FIRMWARE_VERSION, firmware);
    }

    public static Map<UniqueDataType, Object> resolve(UserUpdateDTO userUpdateDTO) {
        User user = userRepository.getByEmail(userUpdateDTO.getEmail()).orElse(null);
        return getResolvedData("email", EXCEPTION_DUPLICATE_EMAIL, user);
    }

    private static Map<UniqueDataType, Object> getResolvedData(String field, String errorCode, HasId saved) {
        Map<UniqueDataType, Object> data = new EnumMap<>(UniqueDataType.class);
        data.put(UniqueDataType.FIELD, field);
        data.put(UniqueDataType.ERROR_CODE, errorCode);
        data.put(UniqueDataType.SAVED_ID, saved != null ? saved.getId() : null);
        return data;
    }
}
