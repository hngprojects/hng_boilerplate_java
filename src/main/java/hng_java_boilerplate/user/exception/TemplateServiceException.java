package hng_java_boilerplate.user.exception;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order(1)
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TemplateServiceException extends RuntimeException {
    public TemplateServiceException(String message) {
        super(message);
    }
}