package ru.posmanager.web.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.validation.Errors;
import ru.posmanager.HasId;
import ru.posmanager.HasIdAndEmail;
import ru.posmanager.HasIdAndName;
import ru.posmanager.domain.bank.Affiliate;
import ru.posmanager.domain.bank.Contractor;
import ru.posmanager.domain.bank.Department;
import ru.posmanager.domain.device.Firmware;
import ru.posmanager.domain.user.User;
import ru.posmanager.repository.bank.AffiliateRepository;
import ru.posmanager.repository.bank.ContractorRepository;
import ru.posmanager.repository.bank.DepartmentRepository;
import ru.posmanager.repository.device.FirmwareRepository;
import ru.posmanager.repository.user.UserRepository;
import ru.posmanager.dto.bank.AffiliateDTO;
import ru.posmanager.dto.bank.ContractorDTO;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.dto.device.FirmwareUpdateDTO;
import ru.posmanager.dto.user.UserUpdateDTO;

import javax.servlet.http.HttpServletRequest;
import java.util.EnumMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static ru.posmanager.web.ExceptionInfoHandler.*;

@Component
public class UniqueValidator implements org.springframework.validation.Validator {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;
    private final FirmwareRepository firmwareRepository;
    private final ContractorRepository contractorRepository;
    private final AffiliateRepository affiliateRepository;
    private final HttpServletRequest request;

    private enum DataType {
        FIELD,
        ERROR_CODE,
        SAVED_ID
    }

    public UniqueValidator(DepartmentRepository departmentRepository, UserRepository userRepository,
                           FirmwareRepository firmwareRepository, ContractorRepository contractorRepository,
                           AffiliateRepository affiliateRepository, @Nullable HttpServletRequest request) {
        this.departmentRepository = departmentRepository;
        this.userRepository = userRepository;
        this.firmwareRepository = firmwareRepository;
        this.contractorRepository = contractorRepository;
        this.affiliateRepository = affiliateRepository;
        this.request = request;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return HasIdAndName.class.isAssignableFrom(clazz)
                || HasIdAndEmail.class.isAssignableFrom(clazz)
                || Map.class.isAssignableFrom(clazz)
                || clazz == FirmwareUpdateDTO.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Assert.notNull(request, "HttpServletRequest missed");
        Map<DataType, Object> data = resolveType(target);
        log.debug("Resolved data: {}", data);
        Integer savedId = data.get(DataType.SAVED_ID) != null ? (Integer) data.get(DataType.SAVED_ID) : null;

        // Handling update request: isn't there already saved value or is it updating itself
        if (request.getMethod().equals("PUT")) {
            if (isNull(savedId) || request.getRequestURI().endsWith(String.valueOf(savedId))) {
                return;
            }
        }
        // Handling create request: there must not be any saved entity with the value
        if (request.getMethod().equals("POST")) {
            if (isNull(savedId)) {
                return;
            }
        }
        errors.rejectValue((String) data.get(DataType.FIELD), (String) data.get(DataType.ERROR_CODE));
    }

    private Map<DataType, Object> resolveType(Object target) {
        if (target instanceof DepartmentDTO departmentDTO) {
            Department department = departmentRepository.getByName(departmentDTO.getName());
            return getResolvedData("name", EXCEPTION_DUPLICATE_DEPARTMENT, department);

        } else if (target instanceof UserUpdateDTO userUpdateDTO) {
            User user = userRepository.getByEmail(userUpdateDTO.getEmail());
            return getResolvedData("email", EXCEPTION_DUPLICATE_EMAIL, user);

        } else if (target instanceof ContractorDTO contractorDTO) {
            Contractor contractor = contractorRepository.getByUnp(contractorDTO.getUnp());
            return getResolvedData("unp", EXCEPTION_DUPLICATE_UNP, contractor);

        } else if (target instanceof FirmwareUpdateDTO firmwareUpdateDTO) {
            int vendorId = firmwareUpdateDTO.getVendorId();
            String version = firmwareUpdateDTO.getVersion();
            Firmware firmware = firmwareRepository.getByVendorAndVersion(vendorId, version);
            return getResolvedData("version", EXCEPTION_FIRMWARE_VERSION, firmware);

        } else if(target instanceof AffiliateDTO affiliateDTO) {
            Affiliate affiliate = affiliateRepository.getByName(affiliateDTO.getName());
            return getResolvedData("name", EXCEPTION_DUPLICATE_AFFILIATE, affiliate);
        } else {
            throw new IllegalStateException("Invalid target for Validator " + target.getClass());
        }
    }

    private Map<DataType, Object> getResolvedData(String field, String errorCode, HasId saved) {
        Map<DataType, Object> data = new EnumMap<>(DataType.class);
        data.put(DataType.FIELD, field);
        data.put(DataType.ERROR_CODE, errorCode);
        data.put(DataType.SAVED_ID, saved != null ? saved.getId() : null);
        return data;
    }
}
