package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.model.request.UserComment;
import ru.posmanager.model.user.User;
import ru.posmanager.to.request.UserCommentUpdateDTO;
import ru.posmanager.to.request.UserCommentDTO;
import ru.posmanager.to.user.UserPreviewDTO;

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
