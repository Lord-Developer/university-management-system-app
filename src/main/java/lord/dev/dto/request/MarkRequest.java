package lord.dev.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MarkRequest {

    @NotBlank
    @Range(min = (long) 0.0, max = (long) 100.0, message = "Mark should be minimum 0 and maximum 100!")
    private double mark;

    @NotBlank
    private long subjectId;

    @NotBlank
    private long studentId;

    @NotBlank
    private long journalId;
}
