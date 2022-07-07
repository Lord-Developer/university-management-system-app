package lord.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lord.dev.model.Student;
import lord.dev.model.Subject;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentInfoResponse {

    private long id;
    private String firstName;
    private String lastName;
    private List<Subject> subjects;
}
