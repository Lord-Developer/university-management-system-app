package lord.dev.service;


import lord.dev.dto.request.FacultyRequest;
import lord.dev.dto.request.FacultyUpdateRequest;
import lord.dev.dto.response.RestAPIResponse;

public interface FacultyService {

    RestAPIResponse create(FacultyRequest facultyRequest);

    RestAPIResponse update(long id, FacultyRequest facultyRequest);


    RestAPIResponse getById(long id);

    RestAPIResponse getFacultyList();
}
