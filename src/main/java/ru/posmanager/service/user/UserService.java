package ru.posmanager.service.user;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.AuthorizedUser;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.domain.user.User;
import ru.posmanager.repository.bank.DepartmentRepository;
import ru.posmanager.repository.user.UserRepository;
import ru.posmanager.dto.user.UserDTO;
import ru.posmanager.dto.user.UserPreviewDTO;
import ru.posmanager.dto.user.UserUpdateDTO;
import ru.posmanager.util.mappers.UserMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.posmanager.util.StringUtil.getEmptyIfNull;
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
        User saved = userRepository.save(userMapper.toEntity(dto));
        return userMapper.toDTO(saved);
    }

    public UserDTO get(int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        return userMapper.toDTO(user);
    }

    public List<UserDTO> getAll() {
        List<User> users = userRepository.getAll().orElse(List.of());
        return userMapper.toDTO(users);
    }

    public List<UserPreviewDTO> getAllUserPreviewDTO() {
        List<User> users = userRepository.getAll().orElse(List.of());
        return userMapper.toUserPreviewDTO(users);
    }

    public List<UserPreviewDTO> getAllFilteredUserPreviewDTO(String lastName, String firstName, String middleName) {
        List<User> users = userRepository
                .getAllFiltered(getEmptyIfNull(lastName), getEmptyIfNull(firstName), getEmptyIfNull(middleName))
                .orElse(List.of());
        return userMapper.toUserPreviewDTO(users);
    }

    @Transactional
    public void update(Map<String, Object> patch, int id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException(User.class, id));
        userRepository.patch(updateFromForeignKeys(user, patch), id);
    }

    public void delete(int id) {
        checkNotFoundWithId(userRepository.delete(id) != 0, User.class, id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email).orElse(null);
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(userMapper.toDTO(user));
    }

    private Map<String, Object> updateFromForeignKeys(User user, Map<String, Object> patch) {
        Map<String, Object> updatePatch = new HashMap<>(patch);
        if (updatePatch.containsKey("departmentId")) {
            Integer departmentId = (Integer) patch.get("departmentId");
            user.setDepartment(departmentRepository.getById(departmentId));
            patch.remove("departmentId");
        }
        updatePatch.computeIfPresent("password", (k, o) -> passwordEncoder.encode((String) o));
        return updatePatch;
    }
}