package ru.posmanager.util.mappers;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.posmanager.domain.bank.BankDevice;
import ru.posmanager.domain.device.DeviceType;
import ru.posmanager.domain.request.Request;
import ru.posmanager.domain.user.User;
import ru.posmanager.dto.request.RequestDTO;
import ru.posmanager.dto.request.RequestPreviewDTO;
import ru.posmanager.dto.request.RequestUpdateDTO;
import ru.posmanager.dto.request.UserCommentDTO;
import ru.posmanager.repository.bank.BankDeviceRepository;
import ru.posmanager.repository.user.UserRepository;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class RequestMapper {
    private final ModelMapper mapper = new ModelMapper();
    private final UserMapper userMapper;
    private final UserCommentMapper userCommentMapper;
    private final UserRepository userRepository;
    private final BankDeviceRepository bankDeviceRepository;

    public RequestMapper(UserMapper userMapper, UserRepository userRepository, BankDeviceRepository bankDeviceRepository,
                         UserCommentMapper userCommentMapper) {
        this.userMapper = userMapper;
        this.userCommentMapper = userCommentMapper;
        this.userRepository = userRepository;
        this.bankDeviceRepository = bankDeviceRepository;
    }

    @PostConstruct
    public void setup() {
        Converter<RequestUpdateDTO, Request> requestUpdateToRequest = ctx -> {
            RequestUpdateDTO source = ctx.getSource();
            User author = userRepository.getById(source.getAuthorId());
            User implementor = userRepository.getById(source.getImplementorId());
            BankDevice bankDevice = bankDeviceRepository.getById(source.getBankDeviceId());
            Request destination = ctx.getDestination();
            destination.setAuthor(author);
            destination.setImplementor(implementor);
            destination.setBankDevice(bankDevice);
            return destination;
        };
        mapper.createTypeMap(RequestUpdateDTO.class, Request.class)
                .addMappings(m -> m.skip(Request::setBankDevice))
                .addMappings(m -> m.skip(Request::setAuthor))
                .addMappings(m -> m.skip(Request::setImplementor))
                .setPostConverter(requestUpdateToRequest);

        Converter<Request, RequestDTO> requestToDto = ctx -> {
            Request source = ctx.getSource();
            User author = source.getAuthor();
            User implementor = source.getImplementor();
            List<UserCommentDTO> comments = userCommentMapper.toDTO(source.getComments());
            RequestDTO destination = ctx.getDestination();
            destination.setAuthor(userMapper.toUserPreviewDTO(author));
            destination.setImplementor(userMapper.toUserPreviewDTO(implementor));
            destination.setComments(comments);
            return destination;
        };
        mapper.createTypeMap(Request.class, RequestDTO.class)
                .addMappings(mapper -> mapper.skip(RequestDTO::setComments))
                .addMappings(mapper -> mapper.skip(RequestDTO::setAuthor))
                .addMappings(mapper -> mapper.skip(RequestDTO::setImplementor))
                .setPostConverter(requestToDto);

        Converter<Request, RequestPreviewDTO> requestToRequestPreviewDto = ctx -> {
            User author = ctx.getSource().getAuthor();
            User implementor = ctx.getSource().getImplementor();
            RequestPreviewDTO destination = ctx.getDestination();
            destination.setAuthor(userMapper.toUserPreviewDTO(author));
            destination.setImplementor(userMapper.toUserPreviewDTO(implementor));
            return destination;
        };
        mapper.createTypeMap(Request.class, RequestPreviewDTO.class)
                .addMappings(mapper -> mapper.map(this::extractDeviceType, RequestPreviewDTO::setDeviceType))
                .addMappings(mapper -> mapper.skip(RequestPreviewDTO::setAuthor))
                .addMappings(mapper -> mapper.skip(RequestPreviewDTO::setImplementor));
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
