package lord.dev.mapper;

import lord.dev.dto.request.UniversityRequest;
import lord.dev.model.Address;
import lord.dev.model.University;
import org.springframework.stereotype.Component;

@Component
public class UniversityMapper {

    public University convertUniversityRequestToUniversityEntity(UniversityRequest universityRequest){
        Address address = new Address();
        address.setCity(universityRequest.getCity());
        address.setDistrict(universityRequest.getDistrict());
        address.setStreet(universityRequest.getStreet());
        University university = new University();
        university.setName(universityRequest.getName());
        university.setAddress(address);
        return university;
    }
}
