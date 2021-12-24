package ru.posmanager.to.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;
import ru.posmanager.model.user.Role;
import ru.posmanager.to.BaseDTO;

import javax.validation.constraints.*;
import java.util.*;

@Data
@NoArgsConstructor
public class UserUpdateDTO extends BaseDTO {

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

    public UserUpdateDTO(UserUpdateDTO u) {
        this(u.id, u.firstName, u.lastName, u.middleName, u.city, u.departmentId, u.email, u.password,
                u.enabled, u.registered, u.roles);
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserUpdateDTO that = (UserUpdateDTO) o;
        return enabled == that.enabled && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName) && Objects.equals(middleName, that.middleName)
                && Objects.equals(city, that.city) && Objects.equals(departmentId, that.departmentId)
                && Objects.equals(email, that.email) && Objects.equals(password, that.password)
                && Objects.equals(registered, that.registered) && Objects.equals(roles, that.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, middleName, city, departmentId, email, password,
                enabled, registered, roles);
    }

    @Override
    public String toString() {
        return "UserUpdateDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", city='" + city + '\'' +
                ", departmentId=" + departmentId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                ", roles=" + roles +
                '}';
    }
}
