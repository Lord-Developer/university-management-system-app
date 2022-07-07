package lord.dev.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
@Getter
@Setter
public class UniversityRequest {

    @NotEmpty
    @Size(min = 5, message = "University Name should have minimum 5 characters!")
    private String name;

    @NotEmpty
    @Size(min = 3, message = "City Name should have minimum 3 characters!")
    private String city;

    @NotEmpty
    @Size(min = 5, message = "District Name should have minimum 5 characters!")
    private String district;

    @NotEmpty
    @Size(min = 5, message = "Street Name should have minimum 5 characters!")
    private String street;
}
