package ru.posmanager.dto.user;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import ru.posmanager.HasIdAndEmail;
import ru.posmanager.dto.BaseDTO;
import ru.posmanager.dto.bank.DepartmentDTO;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class UserPreviewDTO extends BaseDTO implements HasIdAndEmail {

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

    public UserPreviewDTO(UserPreviewDTO to) {
        this(to.id, to.firstName, to.lastName, to.middleName, to.city, to.department, to.email);
    }

    public UserPreviewDTO(String firstName, String lastName, String middleName,
                          String city, DepartmentDTO department, String email) {
        this(null, firstName, lastName, middleName, city, department, email);
    }

    public UserPreviewDTO(Integer id, String firstName, String lastName, String middleName,
                          String city, DepartmentDTO department, String email) {
        super(id);
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.city = city;
        this.email = email;
        this.department = department;
    }
}
