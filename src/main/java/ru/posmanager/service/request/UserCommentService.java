package ru.posmanager.service.request;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.request.UserComment;
import ru.posmanager.dto.request.UserCommentDTO;
import ru.posmanager.dto.request.UserCommentUpdateDTO;
import ru.posmanager.exception.IllegalRequestDataException;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.exception.TimeExpiredException;
import ru.posmanager.repository.request.UserCommentRepository;
import ru.posmanager.util.mappers.UserCommentMapper;
import ru.posmanager.web.security.AuthorizedUserExtractor;

import java.time.LocalDateTime;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class UserCommentService {
    private final UserCommentMapper commentMapper;
    private final UserCommentRepository commentRepository;
    private final AuthorizedUserExtractor authorizedUserExtractor;
    public static final int UPDATING_TIME_MINUTES = 15;

    public UserCommentService(UserCommentMapper commentMapper, UserCommentRepository commentRepository,
                              AuthorizedUserExtractor authorizedUserExtractor) {
        this.commentMapper = commentMapper;
        this.commentRepository = commentRepository;
        this.authorizedUserExtractor = authorizedUserExtractor;
    }

    @Transactional
    public UserCommentDTO create(UserCommentUpdateDTO dto) {
        checkNew(dto);
        UserComment comment = commentMapper.toEntity(dto);
        comment.setCreated(LocalDateTime.now());
        return commentMapper.toDTO(commentRepository.save(comment));
    }

    public UserCommentDTO get(int id) {
        UserComment userComment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserComment.class, id));
        return commentMapper.toDTO(userComment);
    }

    public List<UserCommentDTO> getAll(int requestId) {
        List<UserComment> userComments = commentRepository.getAll(requestId).orElse(List.of());
        return commentMapper.toDTO(userComments);
    }

    @Transactional
    public void update(UserCommentUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        UserComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserComment.class, id));
        checkNotOwn(comment);
        checkNotExpired(comment);
        comment.setContent(dto.getContent());
    }

    public void delete(int id) {
        UserComment comment = commentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(UserComment.class, id));
        checkNotOwn(comment);
        checkNotExpired(comment);
        checkNotFoundWithId(commentRepository.delete(id) != 0, UserComment.class, id);
    }

    private void checkNotExpired(UserComment comment) {
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
