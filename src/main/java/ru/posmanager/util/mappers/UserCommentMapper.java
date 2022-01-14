package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.request.UserComment;
import ru.posmanager.domain.user.User;
import ru.posmanager.dto.request.UserCommentUpdateDTO;
import ru.posmanager.dto.request.UserCommentDTO;
import ru.posmanager.dto.user.UserPreviewDTO;

import java.util.List;

@Component
public class UserCommentMapper {

    private final ModelMapper mapper;

    public UserCommentMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(User.class, UserPreviewDTO.class);

        mapper.createTypeMap(UserComment.class, UserCommentDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getRequest().getId(), UserCommentDTO::setRequestId));

        mapper.createTypeMap(UserCommentDTO.class, UserComment.class);

        mapper.createTypeMap(UserCommentUpdateDTO.class, UserComment.class);
    }

    public UserComment toEntity(UserCommentDTO dto) {
        return mapper.map(dto, UserComment.class);
    }

    public UserComment toEntity(UserCommentUpdateDTO dto) {
        return mapper.map(dto, UserComment.class);
    }

    public UserCommentDTO toDTO(UserComment entity) {
        return mapper.map(entity, UserCommentDTO.class);
    }

    public List<UserCommentDTO> toDTO(List<UserComment> entities) {
        return entities.stream().map(this::toDTO).toList();
    }
}
