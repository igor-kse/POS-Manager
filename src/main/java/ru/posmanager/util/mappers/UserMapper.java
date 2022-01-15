package ru.posmanager.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.bank.Department;
import ru.posmanager.domain.user.User;
import ru.posmanager.dto.bank.DepartmentDTO;
import ru.posmanager.dto.user.UserDTO;
import ru.posmanager.dto.user.UserPreviewDTO;
import ru.posmanager.dto.user.UserUpdateDTO;
import ru.posmanager.repository.bank.DepartmentRepository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.function.Function;

@Component
public class UserMapper {
    private final ModelMapper userMapper = new ModelMapper();
    private final DepartmentMapper departmentMapper;
    private final DepartmentRepository departmentRepository;

    public UserMapper(DepartmentMapper departmentMapper, DepartmentRepository departmentRepository) {
        this.departmentMapper = departmentMapper;
        this.departmentRepository = departmentRepository;
    }

    @PostConstruct
    public void setup() {
        Function<MappingContext<User, ?>, DepartmentDTO> departmentDtoExtractor = ctx ->
                departmentMapper.toDTO(ctx.getSource().getDepartment());

        Converter<UserUpdateDTO, User> userUpdateDtoToUser = ctx -> {
            Department department = departmentRepository.getById(ctx.getSource().getDepartmentId());
            ctx.getDestination().setDepartment(department);
            return ctx.getDestination();
        };

        Converter<User, UserDTO> userToDto = ctx -> {
            ctx.getDestination().setDepartment(departmentDtoExtractor.apply(ctx));
            return ctx.getDestination();
        };

        Converter<User, UserPreviewDTO> userToPreviewDto = ctx -> {
            ctx.getDestination().setDepartment(departmentDtoExtractor.apply(ctx));
            return ctx.getDestination();
        };

        userMapper.createTypeMap(UserUpdateDTO.class, User.class)
                .addMappings(m -> m.skip(User::setDepartment))
                .setPostConverter(userUpdateDtoToUser);

        userMapper.createTypeMap(User.class, UserDTO.class)
                .addMappings(m -> m.skip(UserDTO::setDepartment))
                .setPostConverter(userToDto);

        userMapper.createTypeMap(User.class, UserPreviewDTO.class)
                .addMappings(m -> m.skip(UserPreviewDTO::setDepartment))
                .setPostConverter(userToPreviewDto);
    }

    public User toEntity(UserUpdateDTO dto) {
        return userMapper.map(dto, User.class);
    }

    public UserDTO toDTO(User entity) {
        return userMapper.map(entity, UserDTO.class);
    }

    public List<UserDTO> toDTO(List<User> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    public UserPreviewDTO toUserPreviewDTO(User entity) {
        return userMapper.map(entity, UserPreviewDTO.class);
    }

    public List<UserPreviewDTO> toUserPreviewDTO(List<User> entities) {
        return entities.stream().map(this::toUserPreviewDTO).toList();
    }
}