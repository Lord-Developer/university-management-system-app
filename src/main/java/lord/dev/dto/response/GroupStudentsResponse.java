package lord.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GroupStudentsResponse {
    private long id;
    private String name;
    private List<StudentResponse> students;

}
