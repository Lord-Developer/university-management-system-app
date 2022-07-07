package lord.dev.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class JournalRequest {

    @NotBlank
    @Size(min = 3, message = "Journal Name should have minimum 3 characters!")
    private String name;

    @NotBlank
    private long groupId;
}
