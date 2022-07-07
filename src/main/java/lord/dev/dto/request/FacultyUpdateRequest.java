package lord.dev.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class FacultyUpdateRequest {

    @NotBlank
    @Size(min = 3, message = "Faculty Name should have minimum 3 characters!")
    private String name;
}
