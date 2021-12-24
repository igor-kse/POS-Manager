package ru.posmanager.to.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.Assert;
import ru.posmanager.model.user.Role;
import ru.posmanager.to.BaseDTO;
import ru.posmanager.to.bank.DepartmentDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO extends BaseDTO {

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
    protected DepartmentDTO department;

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

    public UserDTO(UserDTO u) {
        this(u.id, u.firstName, u.lastName, u.middleName, u.city, u.department, u.email, u.password,
                u.enabled, u.registered, u.roles);
    }

    public UserDTO(Integer id, String firstName, String lastName, String middleName, String city,
                   DepartmentDTO department, String email, String password, boolean enabled,
                   Date registered, Set<Role> roles) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.city = city;
        this.department = department;
        this.email = email;
        this.password = password;
        this.enabled = enabled;
        this.registered = registered;
        setRoles(roles);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserDTO userDTO = (UserDTO) o;
        return enabled == userDTO.enabled
                && Objects.equals(department, userDTO.department) && Objects.equals(email, userDTO.email)
                && Objects.equals(password, userDTO.password) && Objects.equals(registered, userDTO.registered)
                && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, middleName, city, department, email, password,
                enabled, registered, roles);
    }
}
