package ru.posmanager.service.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.AuthorizedUser;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.model.user.User;
import ru.posmanager.repository.bank.DepartmentRepository;
import ru.posmanager.repository.user.UserRepository;
import ru.posmanager.to.user.UserDTO;
import ru.posmanager.to.user.UserPreviewDTO;
import ru.posmanager.to.user.UserUpdateDTO;
import ru.posmanager.util.mappers.UserMapper;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import static ru.posmanager.util.StringUtil.emptyStringIfNull;
import static ru.posmanager.util.ValidationUtil.checkNew;
import static ru.posmanager.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UserService implements AuthorizedUserDetailsService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, DepartmentRepository departmentRepository, UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.departmentRepository = departmentRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDTO create(UserUpdateDTO dto) {
        checkNew(dto);
        var user = getEntityFromUpdateDTO(dto);
        user = userRepository.save(user);
        return userMapper.toDTO(user);
    }

    public UserDTO get(int id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        return userMapper.toDTO(user);
    }

    public UserPreviewDTO getUserPreviewDTO(int id) {
        var user = get(id);
        return userMapper.toUserPreviewDTO(user);
    }

    public List<UserDTO> getAll() {
        List<User> users = userRepository.getAll();
        return users != null ? userMapper.toDTO(users) : Collections.emptyList();
    }

    public List<UserPreviewDTO> getAllUserPreviewDTO() {
        List<User> users = userRepository.getAll();
        return users != null ? userMapper.toUserPreviewDTO(users) : Collections.emptyList();
    }

    public List<UserPreviewDTO> getAllFilteredUserPreviewDTO(String lastName, String firstName, String middleName) {
        List<User> users = userRepository.getAllFilteredByName(
                emptyStringIfNull(lastName), emptyStringIfNull(firstName), emptyStringIfNull(middleName)
        );
        return users != null ? userMapper.toUserPreviewDTO(users) : Collections.emptyList();
    }

    @Transactional
    public void update(Map<String, Object> patch, int id) {
        var user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        updateFromForeignKeys(user, patch);
        patch.computeIfPresent("password", (k, o) -> passwordEncoder.encode(k));
        userRepository.patch(patch, id);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id) != 0, User.class, id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(userMapper.toDTO(user));
    }

    private User getEntityFromUpdateDTO(UserUpdateDTO dto) {
        var user = userMapper.toEntity(dto);
        var department = departmentRepository.getById(dto.getDepartmentId());
        user.setDepartment(department);
        user.setRegistered(null);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return user;
    }

    private void updateFromForeignKeys(User user, Map<String, Object> patch) {
        if (patch.containsKey("departmentId")) {
            Integer departmentId = (Integer) patch.get("departmentId");
            var department = departmentRepository.getById(departmentId);
            user.setDepartment(department);
            patch.remove("departmentId");
        }
    }
}