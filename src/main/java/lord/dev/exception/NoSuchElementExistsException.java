package lord.dev.exception;

import org.springframework.http.HttpStatus;

public class NoSuchElementExistsException  extends GlobalExceptionHandler {
    public NoSuchElementExistsException(String message) {
        super(message, HttpStatus.NOT_FOUND.getReasonPhrase(), HttpStatus.NOT_FOUND.value());
    }
}
