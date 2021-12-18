package ru.posmanager.util.mappers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.model.bank.BankDevice;
import ru.posmanager.model.device.DeviceType;
import ru.posmanager.model.request.Request;
import ru.posmanager.model.request.UserComment;
import ru.posmanager.model.user.User;
import ru.posmanager.to.bank.BankDeviceDTO;
import ru.posmanager.to.request.RequestDTO;
import ru.posmanager.to.request.RequestPreviewDTO;
import ru.posmanager.to.request.RequestUpdateDTO;
import ru.posmanager.to.request.UserCommentDTO;
import ru.posmanager.to.user.UserPreviewDTO;

import java.util.List;

@Component
public class RequestMapper {

    private final ModelMapper mapper;

    public RequestMapper() {
        mapper = new ModelMapper();
        mapper.createTypeMap(BankDevice.class, BankDeviceDTO.class);
        mapper.createTypeMap(User.class, UserPreviewDTO.class);

        mapper.createTypeMap(Request.class, RequestPreviewDTO.class)
                .addMappings(mapper -> mapper.map(this::extractDeviceType, RequestPreviewDTO::setDeviceType));

        mapper.createTypeMap(Request.class, RequestUpdateDTO.class)
                .addMappings(mapper -> mapper.map(s -> s.getBankDevice().getId(), RequestUpdateDTO::setBankDeviceId))
                .addMappings(mapper -> mapper.map(s -> s.getAuthor().getId(), RequestUpdateDTO::setAuthorId))
                .addMappings(mapper -> mapper.map(s -> s.getImplementor().getId(), RequestUpdateDTO::setImplementorId));

        mapper.createTypeMap(UserComment.class, UserCommentDTO.class);
        mapper.createTypeMap(Request.class, RequestDTO.class);
    }

    public Request toEntity(RequestDTO dto) {
        return mapper.map(dto, Request.class);
    }

    public Request toEntity(RequestUpdateDTO dto) {
        return mapper.map(dto, Request.class);
    }

    public RequestDTO toDTO(Request entity) {
        return mapper.map(entity, RequestDTO.class);
    }

    public RequestPreviewDTO toPreviewDTO(Request entity) {
        return mapper.map(entity, RequestPreviewDTO.class);
    }

    public List<RequestDTO> toDTO(List<Request> entities) {
        return entities.stream().map(this::toDTO).toList();
    }

    public List<RequestPreviewDTO> toPreviewDTO(List<Request> entities) {
        return entities.stream().map(this::toPreviewDTO).toList();
    }

    private DeviceType extractDeviceType(Request request) {
        return request.getBankDevice().getDevice().getDeviceType();
    }
}
