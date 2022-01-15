package ru.posmanager.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.util.Assert;
import ru.posmanager.HasIdAndEmail;
import ru.posmanager.domain.user.Role;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.web.validator.UniqueDTO;
import ru.posmanager.web.validator.UniqueDataResolver;
import ru.posmanager.web.validator.UniqueDataType;

import javax.validation.constraints.*;
import java.util.*;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class UserUpdateDTO extends BaseDTO implements HasIdAndEmail, UniqueDTO {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    protected String firstName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    protected String lastName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    protected String middleName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    protected String city;

    @NotNull
    @PositiveOrZero
    protected Integer departmentId;

    @Email
    @NotNull
    @Size(min = 5, max = 50)
    protected String email;

    @NotBlank
    @Size(min = 8, max = 255)
    protected String password;

    protected boolean enabled = true;

    protected Date registered;

    protected Set<Role> roles = new HashSet<>();

    public Set<Role> getRoles() {
        return Set.copyOf(roles);
    }

    public void setRoles(Collection<Role> roles) {
        Assert.notNull(roles, "Roles must not be null");
        this.roles.clear();
        this.roles.addAll(roles);
    }

    public UserUpdateDTO(Integer id, String firstName, String lastName, String middleName, String city,
                         Integer departmentId, String email, String password, boolean enabled,
                         Date registered, Set<Role> roles) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.city = city;
        this.departmentId = departmentId;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    @Override
    public Map<UniqueDataType, Object> resolveType() {
        return UniqueDataResolver.resolve(this);
    }
}
