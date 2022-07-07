package lord.dev.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestAPIResponse {
    private String message;
    private int statusCode;
    private Object data;

    public RestAPIResponse(Object data, int statusCode) {
        this.statusCode = statusCode;
        this.data = data;
    }

    private boolean success = true;


    public RestAPIResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public RestAPIResponse(String message) {
        this.message = message;
    }

    public RestAPIResponse(Object data) {
        this.data = data;
    }

    public RestAPIResponse(String message, Object data, int statusCode) {
        this.message = message;
        this.data = data;
        this.statusCode = statusCode;
    }

    public RestAPIResponse(String message, boolean success, int statusCode) {
        this.message = message;
        this.success = success;
        this.statusCode = statusCode;
    }

    public RestAPIResponse(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public RestAPIResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
