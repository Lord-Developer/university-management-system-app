package lord.dev.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SubjectRequest {

    @NotBlank
    @Size(min = 3, message = "Subject Name should have minimum 3 characters!")
    private String name;

    @NotBlank
    private long journalId;
}
