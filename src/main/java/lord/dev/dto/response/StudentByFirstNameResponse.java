package lord.dev.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lord.dev.model.Address;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentByFirstNameResponse {

    private long id;
    private String firstName;
    private String lastName;
    private Address address;
    private String faculty;
    private String group;

}
