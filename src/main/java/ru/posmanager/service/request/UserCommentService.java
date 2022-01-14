package ru.posmanager.service.request;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.IllegalRequestDataException;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.exception.TimeExpiredException;
import ru.posmanager.domain.request.UserComment;
import ru.posmanager.repository.request.RequestRepository;
import ru.posmanager.repository.request.UserCommentRepository;
import ru.posmanager.repository.user.UserRepository;
import ru.posmanager.dto.request.UserCommentUpdateDTO;
import ru.posmanager.dto.request.UserCommentDTO;
import ru.posmanager.util.mappers.UserCommentMapper;
import ru.posmanager.web.security.AuthorizedUserExtractor;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class UserCommentService {
    private final UserRepository userRepository;
    private final RequestRepository requestRepository;
    private final UserCommentRepository userCommentRepository;
    private final AuthorizedUserExtractor authorizedUserExtractor;
    private final UserCommentMapper userCommentMapper;

    public static final int UPDATING_TIME_MINUTES = 15;

    public UserCommentService(UserRepository userRepository, RequestRepository requestRepository,
                              UserCommentRepository userCommentRepository, UserCommentMapper userCommentMapper,
                              AuthorizedUserExtractor authorizedUserExtractor) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
        this.userCommentRepository = userCommentRepository;
        this.userCommentMapper = userCommentMapper;
        this.authorizedUserExtractor = authorizedUserExtractor;
    }

    @Transactional
    public UserCommentDTO create(UserCommentUpdateDTO dto) {
        checkNew(dto);
        var comment = getEntityFromUpdateDTO(dto);
        comment.setCreated(LocalDateTime.now());
        comment = userCommentRepository.save(comment);
        return userCommentMapper.toDTO(comment);
    }

    public UserCommentDTO get(int id) {
        var userComment = userCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserComment.class, id));
        return userCommentMapper.toDTO(userComment);
    }

    public List<UserCommentDTO> getAll(int requestId) {
        List<UserComment> userComments = userCommentRepository.getAll(requestId);
        return userComments != null ? userCommentMapper.toDTO(userComments) : Collections.emptyList();
    }

    @Transactional
    public void update(UserCommentUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        var comment = userCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserComment.class, id));
        checkNotOwn(comment);
        checkIfNotExpired(comment);
        comment.setContent(dto.getContent());
    }

    public void delete(int id) {
        var comment = userCommentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserComment.class, id));
        checkNotOwn(comment);
        checkIfNotExpired(comment);
        checkNotFoundWithId(userCommentRepository.delete(id) != 0, UserComment.class, id);
    }

    public UserComment getEntityFromUpdateDTO(UserCommentUpdateDTO dto) {
        var comment = userCommentMapper.toEntity(dto);
        var user = userRepository.getById(authorizedUserExtractor.authorizedUserId());
        var request = requestRepository.getById(dto.getRequestId());
        comment.setUser(user);
        comment.setRequest(request);
        return comment;
    }

    private void checkIfNotExpired(UserComment comment) {
        if (LocalDateTime.now().minusMinutes(UPDATING_TIME_MINUTES).isAfter(comment.getCreated())) {
            throw new TimeExpiredException("Cannot update the comment after " + UPDATING_TIME_MINUTES + " minutes");
        }
    }

    private void checkNotOwn(UserComment comment) {
        if (authorizedUserExtractor.authorizedUserId() != comment.getUser().id()) {
            throw new IllegalRequestDataException("Attempt to update a comment of an another author");
        }
    }
}
