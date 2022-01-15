package ru.posmanager.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.request.Request;
import ru.posmanager.domain.request.UserComment;
import ru.posmanager.domain.user.User;
import ru.posmanager.dto.request.UserCommentDTO;
import ru.posmanager.dto.request.UserCommentUpdateDTO;
import ru.posmanager.repository.request.RequestRepository;
import ru.posmanager.repository.user.UserRepository;
import ru.posmanager.web.security.AuthorizedUserExtractor;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UserCommentMapper {
    private final ModelMapper mapper = new ModelMapper();
    private final UserMapper userMapper;
    private final AuthorizedUserExtractor authorizedUserExtractor;
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;

    public UserCommentMapper(UserMapper userMapper, AuthorizedUserExtractor authorizedUserExtractor, UserRepository userRepository, RequestRepository requestRepository) {
        this.userMapper = userMapper;
        this.authorizedUserExtractor = authorizedUserExtractor;
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<UserCommentUpdateDTO, UserComment> userCommentUpdatePostConverter = ctx -> {
            User user = userRepository.getById(authorizedUserExtractor.authorizedUserId());
            Request request = requestRepository.getById(ctx.getSource().getRequestId());
            UserComment userComment = ctx.getDestination();
            userComment.setUser(user);
            userComment.setRequest(request);
            return userComment;
        };
        mapper.createTypeMap(UserCommentUpdateDTO.class, UserComment.class)
                .addMappings(m -> m.skip(UserComment::setUser))
                .addMappings(m -> m.skip(UserComment::setRequest))
                .setPostConverter(userCommentUpdatePostConverter);

        Converter<UserComment, UserCommentDTO> userCommentToDtoPostConverter = ctx -> {
            User user = ctx.getSource().getUser();
            ctx.getDestination().setUser(userMapper.toUserPreviewDTO(user));
            return ctx.getDestination();
        };
        mapper.createTypeMap(UserComment.class, UserCommentDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getRequest().getId(), UserCommentDTO::setRequestId))
                .addMappings(m -> m.skip(UserCommentDTO::setUser))
                .setPostConverter(userCommentToDtoPostConverter);
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
