package ru.posmanager.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import ru.posmanager.exception.ErrorInfo;
import ru.posmanager.exception.ErrorType;
import ru.posmanager.exception.IllegalRequestDataException;
import ru.posmanager.exception.NotFoundException;
import ru.posmanager.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;

import static ru.posmanager.exception.ErrorType.*;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final MessageSourceAccessor messageSourceAccessor;

    public static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";
    public static final String EXCEPTION_DUPLICATE_DEPARTMENT = "exception.department.duplicateDepartment";
    public static final String EXCEPTION_DUPLICATE_UNP = "exception.contractor.duplicateUNP";
    public static final String EXCEPTION_DUPLICATE_AFFILIATE = "exception.affiliate.duplicateAffiliate";
    public static final String EXCEPTION_FIRMWARE_VERSION = "exception.firmware.duplicateVendorVersion";

    public ExceptionInfoHandler(MessageSourceAccessor messageSourceAccessor) {
        this.messageSourceAccessor = messageSourceAccessor;
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleNotFoundException(HttpServletRequest request, NotFoundException exception) {
        String notExists = messageSourceAccessor.getMessage("exception.notExists");
        String entityName = messageSourceAccessor.getMessage(exception.getClassName());
        String message = entityName + " (id = " + exception.getId() + ") " + notExists;
        return logAndGetErrorInfo(request, exception, false, DATA_NOT_FOUND, message);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorInfo> handleBindException(HttpServletRequest request, BindException exception) {
        String[] details = exception.getBindingResult().getFieldErrors().stream()
                .map(messageSourceAccessor::getMessage)
                .toArray(String[]::new);
        return logAndGetErrorInfo(request, exception, false, VALIDATION_ERROR, details);
    }

    @ExceptionHandler({IllegalRequestDataException.class, MethodArgumentTypeMismatchException.class, HttpMessageNotReadableException.class})
    public ResponseEntity<ErrorInfo> illegalRequestDataError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, false, WRONG_REQUEST);
    }

    @ExceptionHandler(DataRetrievalFailureException.class)
    public ResponseEntity<ErrorInfo> handleDataRetrievalFailureException(HttpServletRequest request, DataRetrievalFailureException exception) {
        return logAndGetErrorInfo(request, exception, true, WRONG_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleException(HttpServletRequest request, Exception exception) {
        return logAndGetErrorInfo(request, exception, true, APPLICATION_ERROR);
    }

    private ResponseEntity<ErrorInfo> logAndGetErrorInfo(HttpServletRequest request, Exception exception,
                                                         boolean logStackTrace, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(log, request, exception, logStackTrace, errorType);
        return ResponseEntity.status(errorType.getStatus())
                .body(new ErrorInfo(request.getRequestURL().toString(), errorType,
                        messageSourceAccessor.getMessage(errorType.getErrorCode()),
                        details.length != 0 ? details : new String[]{ValidationUtil.getMessage(rootCause)})
                );
    }
}
