package lord.dev.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class StudentRequest {

    @NotBlank(message = "Firstname should not be empty!")
    private String firstName;

    @NotBlank(message = "Lastname should not be empty!")
    private String lastName;

    @NotEmpty
    @Size(min = 3, message = "City Name should have minimum 3 characters!")
    private String city;

    @NotEmpty
    @Size(min = 5, message = "District Name should have minimum 5 characters!")
    private String district;

    @NotEmpty
    @Size(min = 5, message = "Street Name should have minimum 5 characters!")
    private String street;

    @NotBlank(message = "Group ID should not be empty!")
    private long groupId;
}
