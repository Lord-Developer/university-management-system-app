package lord.dev.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GlobalExceptionHandler extends RuntimeException{
    private int code;
    private String status;

    public GlobalExceptionHandler (String message, String status, int code) {
        super(message);
        this.code = code;
        this.status = status;
    }
}
