package ru.posmanager.service.request;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.domain.request.Request;
import ru.posmanager.domain.request.RequestStatus;
import ru.posmanager.dto.request.RequestDTO;
import ru.posmanager.dto.request.RequestPreviewDTO;
import ru.posmanager.dto.request.RequestUpdateDTO;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.repository.request.RequestRepository;
import ru.posmanager.util.mappers.RequestMapper;

import java.util.List;

import static ru.posmanager.util.StringUtil.makeEmptyIfNull;
import static ru.posmanager.util.ValidationUtil.*;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final RequestMapper requestMapper;

    public RequestService(RequestRepository requestRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.requestMapper = requestMapper;
    }

    @Transactional
    public RequestDTO create(RequestUpdateDTO dto) {
        checkNew(dto);
        Request saved = requestRepository.save(requestMapper.toEntity(dto));
        return requestMapper.toDTO(saved);
    }

    public RequestDTO get(int id) {
        Request request = requestRepository.findById(id).orElseThrow(() -> new NotFoundException(Request.class, id));
        return requestMapper.toDTO(request);
    }

    public List<RequestDTO> getAll() {
        List<Request> requests = requestRepository.getAll().orElse(List.of());
        return requestMapper.toDTO(requests);
    }

    public List<RequestPreviewDTO> getAllPreview() {
        List<Request> requests = requestRepository.getAll().orElse(List.of());
        return requestMapper.toPreviewDTO(requests);
    }

    public List<RequestPreviewDTO> getAllFiltered(String title, RequestStatus requestStatus) {
        String safeTitle = makeEmptyIfNull(title);
        List<Request> requests = (requestStatus == null)
                ? requestRepository.getAllFiltered(safeTitle).orElse(List.of())
                : requestRepository.getAllFilteredWithStatus(safeTitle, requestStatus).orElse(List.of());
        return requestMapper.toPreviewDTO(requests);
    }

    @Transactional
    public void update(RequestUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        requestRepository.findById(id).orElseThrow(() -> new NotFoundException(Request.class, id));
        requestRepository.save(requestMapper.toEntity(dto));
    }

    public void delete(int id) {
        checkNotFoundWithId(requestRepository.delete(id) != 0, Request.class, id);
    }
}
