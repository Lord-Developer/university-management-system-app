package lord.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentResponse {

    private long id;
    private String firstName;
    private String lastName;
    private double averageScore;
}
