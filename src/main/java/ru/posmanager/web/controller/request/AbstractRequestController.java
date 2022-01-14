package ru.posmanager.web.controller.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.domain.request.RequestStatus;
import ru.posmanager.service.request.RequestService;
import ru.posmanager.dto.request.RequestDTO;
import ru.posmanager.dto.request.RequestPreviewDTO;
import ru.posmanager.dto.request.RequestUpdateDTO;

import java.util.List;

public abstract class AbstractRequestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RequestService requestService;

    public AbstractRequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    public RequestDTO create(RequestUpdateDTO dto) {
        log.info("create Request from {}", dto);
        return requestService.create(dto);
    }

    public RequestDTO get(int id) {
        log.info("getting Request with id {}", id);
        return requestService.get(id);
    }

    public List<RequestPreviewDTO> getAllFiltered(String title, String requestStatus) {
        log.info("getting all Request filtered by title={}", title);
        RequestStatus status = requestStatus != null ? RequestStatus.valueOf(requestStatus) : null;
        return requestService.getAllFiltered(title, status);
    }

    public List<RequestPreviewDTO> getAllPreview() {
        log.info("getting all Request");
        return requestService.getAllPreview();
    }

    public void update(RequestUpdateDTO dto, int id) {
        log.info("updating Request {} with {}", id, dto);
        requestService.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting Request with id {}", id);
        requestService.delete(id);
    }
}
