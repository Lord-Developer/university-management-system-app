package lord.dev.exception;

import org.springframework.http.HttpStatus;
public class SuchElementAlreadyExistsException extends GlobalExceptionHandler{
    public SuchElementAlreadyExistsException(String message){
        super(message, HttpStatus.ALREADY_REPORTED.getReasonPhrase(), HttpStatus.ALREADY_REPORTED.value());
    }

}
