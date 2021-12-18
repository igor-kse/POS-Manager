package ru.posmanager.service.request;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.model.request.Request;
import ru.posmanager.repository.bank.BankDeviceRepository;
import ru.posmanager.repository.request.RequestRepository;
import ru.posmanager.repository.user.UserRepository;
import ru.posmanager.to.request.RequestDTO;
import ru.posmanager.to.request.RequestPreviewDTO;
import ru.posmanager.to.request.RequestUpdateDTO;
import ru.posmanager.util.mappers.RequestMapper;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.posmanager.util.ValidationUtil.*;

@Service
public class RequestService {
    private final RequestRepository requestRepository;
    private final UserRepository userRepository;
    private final BankDeviceRepository bankDeviceRepository;
    private final RequestMapper requestMapper;

    public RequestService(RequestRepository requestRepository, UserRepository userRepository,
                          BankDeviceRepository bankDeviceRepository, RequestMapper requestMapper) {
        this.requestRepository = requestRepository;
        this.userRepository = userRepository;
        this.bankDeviceRepository = bankDeviceRepository;
        this.requestMapper = requestMapper;
    }

    @Transactional
    public RequestDTO create(RequestUpdateDTO dto) {
        checkNew(dto);
        var request = getEntityFromUpdateDTO(dto);
        request.setCreated(new Date());
        request.setModified(new Date());
        request = requestRepository.save(request);
        return requestMapper.toDTO(request);
    }

    public RequestDTO get(int id) {
        var request = requestRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Request.class, id));
        return requestMapper.toDTO(request);
    }

    public List<RequestDTO> getAll() {
        List<Request> requests = requestRepository.getAll();
        return requests != null ? requestMapper.toDTO(requests) : Collections.emptyList();
    }

    public List<RequestPreviewDTO> getAllPreview() {
        List<Request> requests = requestRepository.getAll();
        return requests != null ? requestMapper.toPreviewDTO(requests) : Collections.emptyList();
    }

    @Transactional
    public void update(RequestUpdateDTO dto, int id) {
        assureIdConsistent(dto, id);
        requestRepository.findById(id).orElseThrow(() -> new NotFoundException(Request.class, id));
        var request = getEntityFromUpdateDTO(dto);
        requestRepository.save(request);
    }

    public void delete(int id) {
        checkNotFoundWithId(requestRepository.delete(id) != 0, Request.class, id);
    }

    private Request getEntityFromUpdateDTO(RequestUpdateDTO dto) {
        var request = requestMapper.toEntity(dto);
        var author = userRepository.getById(dto.getAuthorId());
        var implementor = userRepository.getById(dto.getImplementorId());
        var bankDevice = bankDeviceRepository.getById(dto.getBankDeviceId());
        request.setAuthor(author);
        request.setImplementor(implementor);
        request.setBankDevice(bankDevice);
        return request;
    }
}
