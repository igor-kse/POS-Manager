package ru.posmanager.web.controller.request;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.posmanager.service.request.RequestService;
import ru.posmanager.to.request.RequestDTO;
import ru.posmanager.to.request.RequestPreviewDTO;
import ru.posmanager.to.request.RequestUpdateDTO;

import java.util.List;

public abstract class AbstractRequestController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final RequestService service;

    public AbstractRequestController(RequestService service) {
        this.service = service;
    }

    public RequestDTO create(RequestUpdateDTO requestDTO) {
        log.info("create Request from {}", requestDTO);
        return service.create(requestDTO);
    }

    public RequestDTO get(int id) {
        log.info("getting Request with id {}", id);
        return service.get(id);
    }

    public List<RequestPreviewDTO> getAllPreview() {
        log.info("getting all Request");
        return service.getAllPreview();
    }

    public void update(RequestUpdateDTO dto, int id) {
        log.info("updating Request {} with {}", id, dto);
        service.update(dto, id);
    }

    public void delete(int id) {
        log.info("deleting Request with id {}", id);
        service.delete(id);
    }
}
