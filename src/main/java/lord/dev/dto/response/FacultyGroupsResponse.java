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
public class FacultyGroupsResponse {

    private long id;
    private String faculty;
    private List<GroupResponse> groups;
    private long totalNumberOfStudents;
}
