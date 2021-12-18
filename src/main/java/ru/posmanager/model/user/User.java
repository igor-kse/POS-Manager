package ru.posmanager.model.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.posmanager.model.BaseEntity;
import ru.posmanager.model.bank.Department;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "users")
@Access(AccessType.FIELD)
public class User extends BaseEntity {

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "middle_name")
    private String middleName;

    @NotNull
    @NotBlank
    @Size(min = 2, max = 20)
    @Column(name = "city")
    private String city;

    @Email
    @NotNull
    @Size(min = 5, max = 50)
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank
    @Size(min = 8, max = 255)
    @Column(name = "password", nullable = false)
    private String password;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @Column(name = "enabled", columnDefinition = "bool default true")
    private boolean enabled;

    @Column(name = "registered", columnDefinition = "timestamp default now()")
    private Date registered = new Date();

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles")})
    @ElementCollection(fetch = FetchType.EAGER)
    @BatchSize(size = 200)
    @JoinColumn(name = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<Role> roles = Set.of();

    public User(User u) {
        this(u.id, u.firstName, u.lastName, u.middleName, u.city, u.department,
                u.email, u.password, u.enabled, u.registered, u.roles);
    }

    public User(Integer id, String firstName, String lastName, String middleName, String city, Department department,
                String email, String password, boolean enabled, Date registered, Set<Role> roles) {
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

    public Set<Role> getRoles() {
        return Set.copyOf(roles);
    }

    public void setRoles(Set<Role> roles) {
        this.roles = (roles != null) ? roles : Set.of();
    }
}