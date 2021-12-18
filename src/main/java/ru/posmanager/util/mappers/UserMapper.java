package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.AuthorizedUser;
import ru.posmanager.model.bank.Department;
import ru.posmanager.model.user.User;
import ru.posmanager.to.bank.DepartmentDTO;
import ru.posmanager.to.user.UserDTO;
import ru.posmanager.to.user.UserPreviewDTO;
import ru.posmanager.to.user.UserUpdateDTO;

import java.util.List;

@Component
public class UserMapper {

    private final ModelMapper mapper;

    public UserMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(User.class, UserDTO.class);
        mapper.createTypeMap(Department.class, DepartmentDTO.class);

        mapper.createTypeMap(User.class, AuthorizedUser.class);
        mapper.createTypeMap(User.class, UserPreviewDTO.class);
        mapper.createTypeMap(User.class, UserUpdateDTO.class);

        mapper.createTypeMap(UserDTO.class, UserPreviewDTO.class);
        mapper.createTypeMap(UserDTO.class, UserUpdateDTO.class);
    }

    public User toEntity(UserDTO dto) {
        return mapper.map(dto, User.class);
    }

    public User toEntity(UserUpdateDTO dto) {
        return mapper.map(dto, User.class);
    }

    public UserDTO toDTO(User dto) {
        return mapper.map(dto, UserDTO.class);
    }

    public List<UserDTO> toDTO(List<User> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    public UserPreviewDTO toUserPreviewDTO(User entity) {
        return mapper.map(entity, UserPreviewDTO.class);
    }

    public UserPreviewDTO toUserPreviewDTO(UserDTO dto) {
        return mapper.map(dto, UserPreviewDTO.class);
    }

    public List<UserPreviewDTO> toUserPreviewDTO(List<User> entities) {
        return entities.stream().map(this::toUserPreviewDTO).toList();
    }

    public AuthorizedUser toAuthorizedUser(User user) {
        return mapper.map(user, AuthorizedUser.class);
    }
}